package com.example.pensieve.common.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException)throws IOException, ServletException {
        log.info("{customAccessDeniedHandler: 접근이 막혔을 경우 리다이렉트}");
        res.sendRedirect("/api/exception");
        //접근이 막혔을 시 , exception 페이지로 이동시킴.
    }

}
