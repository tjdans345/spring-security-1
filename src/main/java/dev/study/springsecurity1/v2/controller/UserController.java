package dev.study.springsecurity1.v2.controller;

import dev.study.springsecurity1.v2.domain.dto.UserJoinRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/join")
    public String join(@RequestBody UserJoinRequestDTO userJoinRequestDTO) {
        return
    }

}
