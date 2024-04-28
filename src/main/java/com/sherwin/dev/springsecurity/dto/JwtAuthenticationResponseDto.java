package com.sherwin.dev.springsecurity.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {
    private String token;
    private String refreshToken;
}
