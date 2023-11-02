package com.example.pensieve.common.security;

import com.example.pensieve.common.security.model.MyUserInfos;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        sendErrorResponse(res,"unAuthorized");
    }

    //에러응답 메서드를 새로 생성해쥬기
    private void sendErrorResponse(HttpServletResponse res, String msg) throws IOException{
        ObjectMapper om = new ObjectMapper();

        res.setCharacterEncoding("utf-8");
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(om.writeValueAsString(msg));
    }
        }
