package com.example.pensieve.common.security.model;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class Response {
    private int status;
    private String message;

}
