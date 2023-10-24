package com.example.pensieve.common.security;

import com.example.pensieve.common.config.RedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider JWTPROVIDER;
    private final RedisService redis;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fc) throws IOException, ServletException {
        String token = JWTPROVIDER.resolveToken(req,JWTPROVIDER.TOKEN_TYPE);
//    **doFilterInternal** -> uri.indexOf("swagger") >= 0 || "/{context-path}/v2/api-docs".equals(uri)
//
//        or uri.contains("swagger")||uric.contains("/v2/api/docs")
    }
}
