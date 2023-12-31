package com.example.pensieve.sign;


import com.example.pensieve.board.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
public class SwaggerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService service;
    @MockBean
    private SignService signsvc;


    @Test
    @WithAnonymousUser
    @DisplayName("익명유저")
    public void swagger() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.get("/swagger-ui/index.html"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("익명유저성공기대")
    public void swaggeranonymousUser() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.get("/swagger-ui/index.html"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    @DisplayName("mock유저")
    public void swaggerWithMockUser() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.get("/swagger-ui/index.html"))
                .andExpect(status().isOk());
    }
}
