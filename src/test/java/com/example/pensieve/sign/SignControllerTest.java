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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mvc.perform(post("/api/sign-up")
                .content(String.valueOf(dt))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(content().encoding("UTF-8"))
                .andExpect(status().isCreated());
//        verify(service).signUp(dt);
    }


}