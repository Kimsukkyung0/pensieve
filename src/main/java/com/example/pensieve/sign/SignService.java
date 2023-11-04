package com.example.pensieve.sign;

import com.example.pensieve.common.config.RedisService;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.UserRepository;
import com.example.pensieve.common.security.JwtTokenProvider;
import com.example.pensieve.common.utils.ResultUtils;
import com.example.pensieve.sign.model.SignInDto;
import com.example.pensieve.sign.model.SignInResultDto;
import com.example.pensieve.sign.model.SignupDto;
import com.example.pensieve.sign.model.SignUpResultDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SignService {
    private final PasswordEncoder PW_ENCODER;
    private final RedisService redisService;
    private final UserRepository userRepository;
    private final JwtTokenProvider JWT_PROVIDER;


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




        log.info("{}유저에 대한 access,refresh token 객체 생성",optUsr.get().getEmail());
        UserEntity user = optUsr.get();
        String accessToken = JWT_PROVIDER.generateJwtToken(user.getUserId().toString(),
                Collections.singletonList(user.getRoleType().getCode()), JWT_PROVIDER.ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
        //jwt토큰을 형성하는 과정에서 roles가 우선 리스트 타입이므로 collection, singletonlist로 변환하여 값을 넘겨줌.
        //valid micro second 및 access key는 JWT provider의 맴버필드에서 가져와사용.
        String refreshToken = JWT_PROVIDER.generateJwtToken(user.getUserId().toString(),
                Collections.singletonList(user.getRoleType().getCode()), JWT_PROVIDER.REFRESH_TOKEN_VALID_MS, JWT_PROVIDER.REFRESH_KEY);
        log.info("{}유저에 대한 access,refresh token 생성완료 ",optUsr.get().getEmail());

        log.info("{} redis key 생성중", dto.getEmail());
//        String redisKey = String.format("c:RT",);

        //TODO: redis 에 대한 이해가 지금 당장은 낮으니 작업안함. 11.4작업완료하기


        return SignInResultDto.builder().build();
    }
}
//인증neo4j pw : MlUm8DaSVbvcwFk3UYBz1YOBYS7tglSXHgP07WAvXlQ
//neo4j username : neo4j