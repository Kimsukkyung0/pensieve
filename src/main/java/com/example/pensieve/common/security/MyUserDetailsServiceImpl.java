package com.example.pensieve.common.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String username) {

            return null;
        }

}
