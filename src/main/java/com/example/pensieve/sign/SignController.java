package com.example.pensieve.sign;

import com.example.pensieve.sign.model.SignInDto;
import com.example.pensieve.sign.model.SignupDto;
import com.example.pensieve.sign.model.SignInResultDto;
import com.example.pensieve.sign.model.SignUpResultDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sign")
public class SignController {
    private final SignService SIGNSERVICE;

    /*
    * 1. 회원가입
    * 2. access토큰 발행
    * 3.로그인(과 동시에 access토큰 생성 /저장)
    * 4. 로그아웃
    * 5.이메일 중복확인
    * 6.비밀번호 찾기
    * 7.이메일 찾기
    *
    * */

    @PostMapping("/up")
    public SignUpResultDto signUp(@RequestBody SignupDto dto){
        return SIGNSERVICE.signUp(dto);
    }

    @PostMapping("/in")
    public SignInResultDto signIn(HttpServletRequest req, @RequestBody SignInDto dto){
        String ip = req.getRemoteAddr();
        log.info("[signIn] 로그인을 시도하고 있습니다. email: {}, ip: {}", dto.getEmail(), ip);
        return SIGNSERVICE.signIn(dto,ip);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req){
        SIGNSERVICE.logout(req);
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token","")
                .maxAge(0)
                .path("/")
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE,responseCookie.toString())
                .build();

    }
}
