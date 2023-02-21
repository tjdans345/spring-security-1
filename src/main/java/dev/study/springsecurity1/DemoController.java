package dev.study.springsecurity1;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

    // Spring Filter 디버깅 체킹 해보기
    // doFilter -> bk
    // UsernamePassword 역할 살펴보기
    @GetMapping("/")
    public String helloSecurity() {
        return "hello security";
    }




}
