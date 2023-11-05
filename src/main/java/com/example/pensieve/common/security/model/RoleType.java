package com.example.pensieve.common.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
        USR("ROLE_USER", "일반유저 권한");

        private final String code;
        private final String displayName;
    }

