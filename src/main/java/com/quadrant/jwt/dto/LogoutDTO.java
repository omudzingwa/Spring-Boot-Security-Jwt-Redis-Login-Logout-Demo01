package com.quadrant.jwt.dto;

import lombok.Data;

@Data
public class LogoutDTO {
    private String accessToken;
    private String refreshToken;
}
