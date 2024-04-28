package com.sherwin.dev.springsecurity.mapper;

import com.sherwin.dev.springsecurity.dto.SignupRequestDto;
import com.sherwin.dev.springsecurity.entity.Role;
import com.sherwin.dev.springsecurity.entity.User;

public class UserMapper {
    public static User mapper(SignupRequestDto signupRequestDto) {
        return new User(
                signupRequestDto.getId(),
                signupRequestDto.getFirstName(),
                signupRequestDto.getLastName(),
                signupRequestDto.getEmail(),
                signupRequestDto.getPassword(),
                Role.USER
        );

    }

    public static SignupRequestDto mapper(User user) {
        return new SignupRequestDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                Role.USER
        );
    }
}
