package dev.study.springsecurity1.v2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequestDTO {
    private String username;
    private String password;
}
