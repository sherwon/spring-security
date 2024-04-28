package com.sherwin.dev.springsecurity.service.impl;

import com.sherwin.dev.springsecurity.repository.UserRepository;
import com.sherwin.dev.springsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) {

                return userRepository.findByEmail(username).orElseThrow(
                        () -> new UsernameNotFoundException("No User Found"));

            }
        };
    }
}
