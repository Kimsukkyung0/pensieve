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
    private final JwtTokenProvider JWT_PROVIDER;
    private final RedisService redis;

    //HTTP REQUEST -> resolveTOKEN/ doFILTERINTERNAL

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws IOException, ServletException {
        res.setHeader("Access-Control-Allow-Origin", "*");     //허용할 Origin(요청 url) : "*" 의 경우 모두 허용
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");     //허용할 request http METHOD : POST, GET, DELETE, PUT
        res.setHeader("Access-Control-Max-Age", "3600");     //브라우저 캐시 시간(단위: 초) : "3600" 이면 최소 1시간 안에는 서버로 재요청 되지 않음
        res.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String token = JWT_PROVIDER.resolveToken(req,JWT_PROVIDER.TOKEN_TYPE);
        log.info("doFilterInternal - jwt토큰 추출중 token : {}",token);

        log.info("doFilterInternal - jwt토큰 유효성체크시작");
        if(token != null && JWT_PROVIDER.isValidateToken(token, JWT_PROVIDER.ACCESS_KEY)) {
            String isLogout = redis.getData(token);
            //토큰이 비어있지 않고, 유효한 토큰이고, 로그아웃이 안된상태라면!
            if(ObjectUtils.isEmpty(isLogout)) {
                Authentication authentication = JWT_PROVIDER.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("DoFilter - 토큰 유효성체크완료");
            }
        }
        fc.doFilter(req,res);
        }
    }
