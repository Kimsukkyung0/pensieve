package com.example.pensieve.sign.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInDto {
    private String email;
    private String nickNm;
    private String pw;
}
