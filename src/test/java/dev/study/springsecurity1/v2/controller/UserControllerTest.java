package dev.study.springsecurity1.v2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.study.springsecurity1.v2.domain.dto.UserJoinRequestDTO;
import dev.study.springsecurity1.v2.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper; // JavaObject -> JsonData

    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {

        String userName = "meteor";
        String password = "meteor33";

        mockMvc.perform(post("/api/v1/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequestDTO(userName, password))
                ) // http request 에 값을 보낼때는 바이트로 전송된다.
        )
                .andDo(print())
                .andExpect(status().isOk());

    }


}