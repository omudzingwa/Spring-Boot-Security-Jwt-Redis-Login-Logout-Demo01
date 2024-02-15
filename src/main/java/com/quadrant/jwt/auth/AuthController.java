package com.quadrant.jwt.auth;

import com.quadrant.jwt.dto.LoginRequestDTO;
import com.quadrant.jwt.dto.LogoutDTO;
import com.quadrant.jwt.dto.RefreshTokenDTO;
import com.quadrant.jwt.dto.SignupRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated SignupRequestDTO signup, Errors errors){
        // validation check
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return authService.signUp(signup);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated LoginRequestDTO login, Errors errors){
        // validation check
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return authService.login(login);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshUserToke(@Validated RefreshTokenDTO refresh, Errors errors){
        // validation check
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return authService.refreshUserToken(refresh);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Validated LogoutDTO logout, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return authService.logout(logout);
    }

    @GetMapping("/authority")
    public ResponseEntity<?> authority() {
        log.info("ADD ROLE_ADMIN");
        return authService.authority();
    }

}
