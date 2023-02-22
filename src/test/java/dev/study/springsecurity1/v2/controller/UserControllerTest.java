package dev.study.springsecurity1.v2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.study.springsecurity1.v2.domain.dto.UserJoinRequestDTO;
import dev.study.springsecurity1.v2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Mock Test
@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper; // JavaObject -> JsonData

    @BeforeEach // @BeforeEach 어노테이션은 각 테스트 메서드가 실행되기 전에 해당 메서드를 실행하여, 테스트 메서드에서 사용할 객체를 초기화하는 역할
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Test Code
     * 회원 가입 성공 case
     * @throws Exception
     */
    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {

        String userName = "meteor";
        String password = "meteor33";

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequestDTO(userName, password))
                )) // http request 에 값을 보낼때는 바이트로 전송된다.
                .andDo(print())
                .andExpect(status().isOk());

    }

    /**
     * Test Code
     * 회원 가입 실패 case
     * @throws Exception
     */
    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    void joinFail() throws Exception {

        String userName = "meteor";
        String password = "meteor33";

        when(userService.join(any(), any()))
                .thenThrow(new RuntimeException("해당 유저아이디가 이미 존재합니다."));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequestDTO(userName, password))
                        ) // http request 에 값을 보낼때는 바이트로 전송된다.
                )
                .andDo(print())
                .andExpect(status().isConflict());

    }

}