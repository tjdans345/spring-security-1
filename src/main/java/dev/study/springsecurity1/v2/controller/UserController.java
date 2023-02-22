package dev.study.springsecurity1.v2.controller;

import dev.study.springsecurity1.v2.domain.dto.UserJoinRequestDTO;
import dev.study.springsecurity1.v2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequestDTO userJoinRequestDTO) {
        userService.join(userJoinRequestDTO.getUserName(), userJoinRequestDTO.getPassword());
        return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
    }

}
