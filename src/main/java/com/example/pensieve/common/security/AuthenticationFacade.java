package com.example.pensieve.common.security;

import com.example.pensieve.common.security.model.MyUserInfos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

    @Component
    @Slf4j
    public class AuthenticationFacade{
        public MyUserInfos getLoginUserInfo() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //인증정보 contextholder에서 인증정보 받아오기
            log.info("auth: {}", auth);
            if ("anonymousUser".equals(auth.getPrincipal())) {
                throw new RuntimeException("로그인 필요");
            }
            //익명유저일 경우, 로그인 필요 에러 예외처리
            return (MyUserInfos) auth.getPrincipal();
        }

        public Long getLoginUserPk() {
            return getLoginUserInfo().getUserId();
        }
    }


