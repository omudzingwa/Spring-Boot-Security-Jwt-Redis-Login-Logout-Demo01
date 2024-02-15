package com.quadrant.jwt.tokenutils;

import com.quadrant.jwt.dto.LoginResponseDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE="Bearer";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.accessTokenDuration}")
    private long accessTokenDuration;
    @Value("${jwt.refreshTokenDuration}")
    private long refreshTokenDuration;

    //SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //A method that generates an AccessToken and a RefreshToken with the user's information
    public LoginResponseDTO generateToken(Authentication authentication) {
        // Get Permissions
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date createdTime = new Date();
        // Generate Access Token
        Date accessTokenExpiresIn = new Date(now + accessTokenDuration);
        String accessToken = Jwts.builder()
                .issuer("quadrant")
                .subject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .issuedAt(createdTime)
                .expiration(accessTokenExpiresIn)
                .signWith(secretKey)
                .compact();

        // Generate Refresh Token
        String refreshToken = Jwts.builder()
                .issuer("quadrant")
                .issuedAt(new Date())
                .expiration(new Date(now + refreshTokenDuration))
                .signWith(secretKey)
                .compact();

        return LoginResponseDTO.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(refreshTokenDuration)
                .build();
    }

    // A method that decrypts a JWT token and retrieves the information contained in the token
    public Authentication getAuthentication(String accessToken) {
        // Token Decryption
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("A token with no privilege information.");
        }

        // Get permission information from a claim
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // Create a UserDetails object to return Authentication
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // Methods for verifying token information
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public Long getExpiration(String accessToken) {
        // accessToken remaining time
        Date expiration = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getExpiration();
        // Current Time
        long now = new Date().getTime();
        return (expiration.getTime() - now);
    }


}
