package com.example.pensieve.sign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SignController {
    private final SignService SIGNSERVICE;

    /*
    * 1. 회원가입
    * 2. access토큰 재발행
    * 3.로그인(과 동시에 access토큰 생성 /저장)
    * 4. 로그아웃
    * 5.이메일 중복확인
    * 6.비밀번호 찾기
    * 7.이메일 찾기
    *
    * */

}
