package com.example.pensieve.common.security;

import com.example.pensieve.common.repository.ServiceAdminRepository;
import com.example.pensieve.common.repository.UserRepository;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final UserRepository usrRep;
    private final ServiceAdminRepository adminRep;

    public String generateJwtProvider(String strIuser, List<String> role, long token_valid_ms, Key key){
        Date now = new Date();
        return null;
    }

    //Constructor of this class
    public JwtTokenProvider(@Value("${springboot.jwt.access-secret}") String accessSecretKey
                            , @Value("${springboot.jwt.refresh-secret}") String refreshSecretKey
                            , @Value("${springboot.jwt.token-type}") String tokenType
                            , UserRepository usrRep , ServiceAdminRepository adminRep) {

        byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecretKey);
        this.ACCESS_KEY = Keys.hmacShaKeyFor(accessKeyBytes);
        byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecretKey);
        this.REFRESH_KEY = Keys.hmacShaKeyFor(refreshKeyBytes);
        this.TOKEN_TYPE = tokenType;
        this.usrRep = usrRep;
        this.adminRep = adminRep;
    }
}
