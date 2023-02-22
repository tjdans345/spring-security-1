//package dev.study.springsecurity1.v1;
//
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Configuration
//public class InMemoryTest {
//
//    // Component Scan 및 스프링 빈 생성 테스트용 임시 메서드
////    @PostConstruct
////    public void test() {
////        System.out.printf("실행이요");
////    }
//
//    @Bean
//    public UserDetailsService getUsers() {
//
//        log.info("인메모리 유저 생성");
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}password") // {noop} 암호화 시키지 않겠다는 말
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}password")
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//
//    }
//
//
//
//
//}
