package com.sherwin.dev.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SigninRequestDto {
    private String email;
    private String password;
}
