package com.quadrant.jwt.auth;

import com.quadrant.jwt.config.GetPrincipal;
import com.quadrant.jwt.dto.*;
import com.quadrant.jwt.tokenutils.JwtTokenProvider;
import com.quadrant.jwt.userroles.Role;
import com.quadrant.jwt.users.User;
import com.quadrant.jwt.users.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;
    //private final RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();

    public ResponseEntity<?> signUp(SignupRequestDTO signUp) {
        if (userRepository.findByUsername(signUp.getUsername()).isPresent()) {
            return new ResponseEntity<>("The username already exists.", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .firstname(signUp.getFirstname())
                .lastname(signUp.getLastname())
                .username(signUp.getUsername())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return new ResponseEntity<>("You have successfully signed up", HttpStatus.CREATED);
    }

    public ResponseEntity<?> login(LoginRequestDTO login) {

        if (userRepository.findByUsername(login.getUsername()).orElse(null) == null) {
            return new ResponseEntity<>("Username not found", HttpStatus.BAD_REQUEST);
        }

        // 1. Create an Authentication object based on Login username and password
        // In this case, authentication checks whether the authenticated value is false
        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();

        // 2. The part where the actual verification (user password check) takes place
        // When the authenticate method is executed, the loadUserByUsername method created by CustomUserDetailsService is executed.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. Generate a JWT token based on authentication information
        LoginResponseDTO userToken = jwtTokenProvider.generateToken(authentication);

        // 4. RefreshToken Redis storage (auto-delete via expirationTime setting)
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), userToken.getRefreshToken(), userToken.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    public ResponseEntity<?> refreshUserToken(RefreshTokenDTO refreshTokenDTO) {
        // 1. Refresh Token validation
        if (!jwtTokenProvider.validateToken(refreshTokenDTO.getRefreshToken())) {
            return new ResponseEntity<>("Refresh token is invalid", HttpStatus.BAD_REQUEST);
        }

        // 2. Get the User email from the Access Token.
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshTokenDTO.getAccessToken());

        // 3. In Redis, get the Refresh Token value stored based on the User email.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // Handle (extra) logged out and RefreshToken doesn't exist in Redis
        if(ObjectUtils.isEmpty(refreshToken)) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
        if(!refreshToken.equals(refreshTokenDTO.getRefreshToken())) {
            return new ResponseEntity<>("The refresh token information does not match.", HttpStatus.BAD_REQUEST);
        }

        // 4. Generate a new token
        LoginResponseDTO userToken= jwtTokenProvider.generateToken(authentication);

        // 5. RefreshToken Redis updates
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), userToken.getRefreshToken(), userToken.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return new ResponseEntity<>("Token information has been updated", HttpStatus.OK);
    }

    public ResponseEntity<?> logout(LogoutDTO logout) {
        // 1. Access Token verification
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) {
            return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
        }
        // 2. Get the username from Access Token
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());
        // 3. Redis checks whether there is a Refresh Token stored as the corresponding user email and deletes it if there is.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Delete Refresh Token
            redisTemplate.delete("RT:" + authentication.getName());
        }
        // 4. Get the validity time of the Access Token and save it as a BlackList
        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue().set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
        return new ResponseEntity<>("You have successfully logged out", HttpStatus.OK);
    }

    public ResponseEntity<?> authority() {
        // About the authentication username contained in the SecurityContext
        String username = GetPrincipal.getCurrentUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No authentication information."));
        // add ROLE_ADMIN
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return new ResponseEntity<>("Role ADMIN added to user", HttpStatus.OK);
    }

}
