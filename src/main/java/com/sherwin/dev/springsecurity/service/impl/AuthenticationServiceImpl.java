package com.sherwin.dev.springsecurity.service.impl;

import com.sherwin.dev.springsecurity.dto.JwtAuthenticationResponseDto;
import com.sherwin.dev.springsecurity.dto.RefreshTokenRequestDto;
import com.sherwin.dev.springsecurity.dto.SigninRequestDto;
import com.sherwin.dev.springsecurity.dto.SignupRequestDto;
import com.sherwin.dev.springsecurity.entity.User;
import com.sherwin.dev.springsecurity.mapper.UserMapper;
import com.sherwin.dev.springsecurity.repository.UserRepository;
import com.sherwin.dev.springsecurity.service.AuthenticationService;
import com.sherwin.dev.springsecurity.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public SignupRequestDto signUp(SignupRequestDto signupRequestDto) {
        User user = UserMapper.mapper(signupRequestDto);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        User userRegister = userRepository.save(user);
        return UserMapper.mapper(userRegister);
    }

    @Override
    public JwtAuthenticationResponseDto signin(SigninRequestDto signinRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequestDto.getEmail(),
                        signinRequestDto.getPassword()));

        var user = userRepository.findByEmail(signinRequestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("invalid user email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
        jwtAuthenticationResponseDto.setToken(jwt);
        jwtAuthenticationResponseDto.setRefreshToken(refreshToken);
        return jwtAuthenticationResponseDto;
    }

    @Override
    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String userEmail = jwtService.extractUserName(refreshTokenRequestDto.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequestDto.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
            jwtAuthenticationResponseDto.setToken(jwt);
            jwtAuthenticationResponseDto.setRefreshToken(refreshTokenRequestDto.getToken());
            return jwtAuthenticationResponseDto;
        }
        return null;
    }
}
