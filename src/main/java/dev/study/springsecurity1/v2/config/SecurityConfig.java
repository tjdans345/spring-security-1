package dev.study.springsecurity1.v2.config;


import dev.study.springsecurity1.v2.filter.JwtTokenFilter;
import dev.study.springsecurity1.v2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic().disable() // ui 쪽으로 접근을 막는 설정
                .csrf().disable() // 크로스 사이트 기능 사용안하는 설정
                .cors().and()
//                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        // 모든 디스패처 유형을 필터링해야 하는지 여부를 설정
                        .shouldFilterAllDispatcherTypes(false)
                        .requestMatchers( "/api/v1/users/join", "/api/v1/users/login")
                        .permitAll()
                        .anyRequest().authenticated()
                )
//                .oauth2Login()
//                .and()
                // 토큰을 검증하려면 시크릿 키가 필요하기 때문에 파라미터로 넣어준다.
                // JWT 필터가 UsernamePasswordAuthenticationFilter 대신하여 인증을 한다.
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
