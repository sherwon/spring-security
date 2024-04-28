package com.sherwin.dev.springsecurity.controller;

import com.sherwin.dev.springsecurity.dto.JwtAuthenticationResponseDto;
import com.sherwin.dev.springsecurity.dto.RefreshTokenRequestDto;
import com.sherwin.dev.springsecurity.dto.SigninRequestDto;
import com.sherwin.dev.springsecurity.dto.SignupRequestDto;
import com.sherwin.dev.springsecurity.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignupRequestDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        return new ResponseEntity<>(authenticationService.signUp(signupRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@RequestBody SigninRequestDto signinRequestDto) {
        return new ResponseEntity<>(authenticationService.signin(signinRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthenticationResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return new ResponseEntity<>(authenticationService.refreshToken(refreshTokenRequestDto), HttpStatus.CREATED);
    }
}
