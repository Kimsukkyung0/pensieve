package com.example.pensieve.common.security;

import com.example.pensieve.common.config.RedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider JWTPROVIDER;
    private final RedisService redis;

    //HTTP REQUEST -> resolveTOKEN/ doFILTERINTERNAL

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws IOException, ServletException {
        String uri = req.getRequestURI();
        //요청 uri가 swagger 나 api-docs 를 포함한다면 토큰 추츨하기
        if (uri.contains("/api")){
        String token = JWTPROVIDER.resolveToken(req,JWTPROVIDER.TOKEN_TYPE);
        log.info("doFilterInternal - jwt토큰 추출중 token : {}",token);

        log.info("doFilterInternal - jwt토큰 유효성체크시작");
        if(token != null && JWTPROVIDER.isValidateToken(token, JWTPROVIDER.ACCESS_KEY)) {
            String isLogout = redis.getData(token);
            //토큰이 비어있지 않고, 유효한 토큰이고, 로그아웃이 안된상태라면!
            if(ObjectUtils.isEmpty(isLogout)) {
                Authentication authentication = JWTPROVIDER.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("DoFilter - 토큰 유효성체크완료");
            }
        }
        fc.doFilter(req,res);


        }

//        Boolean tmp = uri.indexOf("swagger") >= 0 || "/{context-path}/v2/api-docs".equals(uri) ||uri.contains("swagger");

//    **doFilterInternal** -> uri.indexOf("swagger") >= 0 || "/{context-path}/v2/api-docs".equals(uri)
//
//        or uri.contains("swagger")||uric.contains("/v2/api/docs")
    }
}
