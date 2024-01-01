package com.example.pensieve.sign;

import com.example.pensieve.common.config.RedisService;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.UserRepository;
import com.example.pensieve.common.security.AuthenticationFacade;
import com.example.pensieve.common.security.JwtTokenProvider;
import com.example.pensieve.common.security.model.MyUserInfos;
import com.example.pensieve.common.security.model.RoleType;
import com.example.pensieve.common.utils.ResultUtils;
import com.example.pensieve.sign.model.SignInDto;
import com.example.pensieve.sign.model.SignInResultDto;
import com.example.pensieve.sign.model.SignupDto;
import com.example.pensieve.sign.model.SignUpResultDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private final AuthenticationFacade authFacade;


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
                log.info("[signup] 정상 처리 완료");
                ResultUtils.setSuccessResult(result);
                return result; }
    }

    public SignInResultDto signIn(SignInDto dto, String ip){
        Optional<UserEntity> optUsr = userRepository.findByEmail(dto.getEmail());
        if(optUsr.isEmpty()){
            throw new RuntimeException("[login] 존재하지 않는 이메일입니다");
        } else if (!PW_ENCODER.matches(dto.getPw(),optUsr.get().getPw())) {//이메일이 존재하고,
            throw new RuntimeException("[login] 비밀번호가 일치하지 않습니다");
        }

        UserEntity user = optUsr.get();
        String loginUserEmail = user.getEmail();
        log.info("[login] {}유저에 대한 access,refresh token 객체 생성",loginUserEmail);
        String accessToken = JWT_PROVIDER.generateJwtToken(String.valueOf(user.getUserId()),
                Collections.singletonList(user.getRoleType().getCode()), JWT_PROVIDER.ACCESS_TOKEN_VALID_MS, JWT_PROVIDER.ACCESS_KEY);
        //jwt토큰을 형성하는 과정에서 roles가 우선 리스트 타입이므로 collection, singletonlist로 변환하여 값을 넘겨줌.
        //valid micro second 및 access key는 JWT provider의 맴버필드에서 가져와사용.
        String refreshToken = JWT_PROVIDER.generateJwtToken(String.valueOf(user.getUserId()),
                Collections.singletonList(user.getRoleType().getCode()), JWT_PROVIDER.REFRESH_TOKEN_VALID_MS, JWT_PROVIDER.REFRESH_KEY);
        log.info("{}유저에 대한 access,refresh token 생성완료 ",loginUserEmail);

        log.info("[login] {} redis key 생성중", dto.getEmail());

        //유저권한에따른 rediskey 생성
        String redisKey = (user.getRoleType().getCode().equals("ADMIN"))?
            String.format("c:RT(%s):ADMIN:%s:%s", "server", user.getUserId(), ip) :
            String.format("c:RT(%s):%s:%s", "server", user.getUserId(), ip);

        log.info("[login] {} redis key 생성완료", dto.getEmail());

        if(redisService.getData(redisKey) != null){
            log.info("[login] redis에 {}의 로그인 값이 존재합니다", user.getUserId());
            redisService.deleteData(redisKey);
        }

        log.info("[login] redis 통신중..");
        redisService.setData(redisKey,refreshToken);

        SignInResultDto signInResult = SignInResultDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(user.getRoleType().getCode())
                .build();


        ResultUtils.setSuccessResult(signInResult);
        log.info("[login] 로그인완료");

        return signInResult;
    }

    public void logout(HttpServletRequest req){
        String accessToken = JWT_PROVIDER.resolveToken(req,JWT_PROVIDER.TOKEN_TYPE);
        Long userPk = authFacade.getLoginUserPk();
        String ip = req.getRemoteAddr();
        MyUserInfos userInfo = authFacade.getLoginUserInfo();
        String redisKey = "ROLE_USER".equals(userInfo.getRoles().get(0)) ?
                String.format("c:RT(%s):%s:%s", "Server", userPk, ip):
                String.format("c:RT(%s):ADMIN:%s:%s", "Server", userPk, ip);

        String refreshTokenInRedis = redisService.getData(redisKey);
        if (refreshTokenInRedis != null) {
            redisService.deleteData(redisKey);
        }

        long expiration = JWT_PROVIDER.getTokenExpirationTime(accessToken, JWT_PROVIDER.ACCESS_KEY) -
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        log.info("expiration: {}", expiration);
        log.info("localDateTime-getTime(): {}", LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        redisService.setDataExpire(accessToken, "logout", expiration);
    }

}
//인증neo4j pw : MlUm8DaSVbvcwFk3UYBz1YOBYS7tglSXHgP07WAvXlQ
//neo4j username : neo4j