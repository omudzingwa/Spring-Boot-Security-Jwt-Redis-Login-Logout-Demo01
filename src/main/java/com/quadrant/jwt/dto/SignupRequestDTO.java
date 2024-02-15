package com.quadrant.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
public class SignupRequestDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
