package com.example.pensieve.common.security;

import com.example.pensieve.common.repository.ServiceAdminRepository;
import com.example.pensieve.common.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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



    /*받아오는 정보는 총 네가지,
    1. strIuser- (String)userId : 문자열로 변환된 유저의 pk
    2. (collection)role - roletype.getcode 로 코드를 받아오고 있음.
    3. 토큰 당 유효시간
    4. 키() - 토큰타입별 지정해 놓은 yaml을 불러오는 넊낌 . generatejwt를 호출할때 value로 , 값을 줄거임
    */

    public String generateJwtToken(String strIuserPk, List<String> roles, long token_valid_ms, Key key){
        Date now = new Date();
        //jwts 클래스의 builder 메서드를 이용해 토큰 생성
        String token = Jwts.builder().setClaims(createClaim(strIuserPk,roles))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+token_valid_ms))
                .signWith(key).compact();
        /*등록된 클레임 중에서는 iat, exp 두개를 저장,**
         **비공개클레임 중  key 값을 token에 담고있다.*/
        return token;
    }

    private Claims createClaim(String strIuserPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(strIuserPk);
        claims.put("roles",roles);
        return claims;
    }

    //Constructor of this class

}
