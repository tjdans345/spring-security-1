package dev.study.springsecurity1.v1;

import lombok.Builder;
import lombok.Data;

@Data
public class DemoDTO {

    private String username;

    private String sessionId;

    @Builder
    public DemoDTO(String username, String sessionId) {
        this.username = username;
        this.sessionId = sessionId;
    }
}
