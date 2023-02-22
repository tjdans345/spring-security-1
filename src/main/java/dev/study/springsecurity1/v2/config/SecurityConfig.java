package dev.study.springsecurity1.v2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic().disable() // ui 쪽으로 접근을 막는 설정
                .csrf().disable() // 크로스 사이트 기능 사용안하는 설정
                .cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        // 모든 디스패처 유형을 필터링해야 하는지 여부를 설정
                        .shouldFilterAllDispatcherTypes(false)
                        .requestMatchers("/api/**", "/api/v1/users/join", "/api/v1/users/login")
                        .permitAll()
                )
                .build();
    }

}
