package com.sherwin.dev.springsecurity.service;

import com.sherwin.dev.springsecurity.dto.JwtAuthenticationResponseDto;
import com.sherwin.dev.springsecurity.dto.RefreshTokenRequestDto;
import com.sherwin.dev.springsecurity.dto.SigninRequestDto;
import com.sherwin.dev.springsecurity.dto.SignupRequestDto;

public interface AuthenticationService {
    SignupRequestDto signUp(SignupRequestDto signupRequestDto);

    JwtAuthenticationResponseDto signin(SigninRequestDto signinRequestDto);

    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
