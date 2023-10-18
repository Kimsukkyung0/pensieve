package com.example.pensieve.sign;

import com.example.pensieve.common.config.RedisService;
import com.example.pensieve.common.repository.UserRepository;
import com.example.pensieve.sign.model.SignInDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SignService {
//    private final PasswordEncoder PW_ENCODER;
    private final RedisService redisService;
    private final UserRepository userRepository;


    public void signUp(SignInDto dto){

    }
}
//인증neo4j pw : MlUm8DaSVbvcwFk3UYBz1YOBYS7tglSXHgP07WAvXlQ
//neo4j username : neo4j