package dev.study.springsecurity1.v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncoderConfig {

    // SecurityConfig , EncoderConfig 는 다른 클래스에 설정해준다 ( 순환 참조 문제 방지 )
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
