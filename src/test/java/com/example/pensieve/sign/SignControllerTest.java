package com.example.pensieve.sign;

import com.example.pensieve.sign.model.SignInDto;
import com.example.pensieve.sign.model.SignUpResultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(SignController.class)
class SignControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    SignService service;

    @Test
    @DisplayName("회원가입test")
    void signUp() throws Exception{
        SignInDto dt = SignInDto.builder().email("smk93021@gmail.com").pw("testpw").nickNm("g").build();
        SignUpResultDto resultDto = new SignUpResultDto();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String resJson = om.writeValueAsString(resultDto);

        
    }


}