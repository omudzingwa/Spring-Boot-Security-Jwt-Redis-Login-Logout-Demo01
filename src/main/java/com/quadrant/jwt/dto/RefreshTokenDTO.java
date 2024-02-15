package com.quadrant.jwt.dto;

import lombok.Data;

@Data
public class RefreshTokenDTO {
    private String accessToken;
    private String refreshToken;
}
