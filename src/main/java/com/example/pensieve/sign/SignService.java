package com.example.pensieve.sign;

import com.example.pensieve.common.config.RedisService;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.UserRepository;
import com.example.pensieve.common.utils.ResultUtils;
import com.example.pensieve.sign.model.SignInDto;
import com.example.pensieve.sign.model.SignInResultDto;
import com.example.pensieve.sign.model.SignupDto;
import com.example.pensieve.sign.model.SignUpResultDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SignService {
    private final PasswordEncoder PW_ENCODER;
    private final RedisService redisService;
    private final UserRepository userRepository;


    public SignUpResultDto signUp(SignupDto dto){
        Optional<UserEntity> uEntity = userRepository.findByEmail(dto.getEmail());
        SignUpResultDto result = new SignUpResultDto();

        if(uEntity.isPresent()){
            ResultUtils.setFailResult(result);
            return result;
            }
        else {
                userRepository.save(UserEntity.builder()
                        .email(dto.getEmail())
                        .pw(PW_ENCODER.encode(dto.getPw()))
                        .nickNm(dto.getNickNm())
                        .build());
                log.info("[getSignUpResult] 정상 처리 완료");
                ResultUtils.setSuccessResult(result);
                return result; }


    }

    public SignInResultDto signIn(SignInDto dto, String ip){
        Optional<UserEntity> optUsr = userRepository.findByEmail(dto.getEmail());
        if(optUsr.isEmpty()){
            throw new RuntimeException("존재하지 않는 이메일입니다");
        } else if (!PW_ENCODER.matches(dto.getPw(),optUsr.get().getPw())) {//이메일이 존재하고,
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
        
    }
}
//인증neo4j pw : MlUm8DaSVbvcwFk3UYBz1YOBYS7tglSXHgP07WAvXlQ
//neo4j username : neo4j