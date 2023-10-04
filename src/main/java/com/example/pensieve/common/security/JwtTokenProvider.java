package com.example.pensieve.common.security;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtTokenProvider {
    public final Key ACCESS_KEY;
    public final Key REFRESH_KEY;

    public final String TOKEN_TYPE;
    public final long ACCESS_TOKEN_VALID_MS = 1000L *60 *30;//30분
    public final long REFRESH_TOKEN_VALID_MS = 1_296_000_000L; //15일 정도

    public String generateJwtProvider(String strIuser, List<String> role, long token_valid_ms, Key key){
        Date now = new Date();
        return null;
    }
    
    public JwtTokenProvider(@Value("${springboot.jwt.access-secret}") String accessSecretKey){

    }
}
