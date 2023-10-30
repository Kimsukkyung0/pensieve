package com.example.pensieve.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//패스워드 해독 기능설정
@Configuration
public class PwEncoderConfig {
    @Bean
    public PasswordEncoder pwEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
