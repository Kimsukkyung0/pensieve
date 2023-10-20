package com.example.pensieve.sign.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignInResultDto extends SignUpResultDto{
    private String accessToken;
    private String refreshToken;
    private String role;

}
