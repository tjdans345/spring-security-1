//package dev.study.springsecurity1.v1;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//public class DemoController {
//
//    // Spring Filter 디버깅 체킹 해보기
//    // doFilter -> bk
//    // UsernamePassword 역할 살펴보기
//    @GetMapping("/")
//    public String helloSecurity() {
//        return "hello security";
//    }
//
//    @GetMapping("/session")
//    public DemoDTO hello(@AuthenticationPrincipal User user, HttpSession httpSession) {
//
//        String username = "empty";
//        String sessionId = "empty";
//
//        if(user == null) {
//            log.info("user is null");
//        } else {
//            sessionId = httpSession.getId();
//            username = user.getUsername();
//        }
//
//        return DemoDTO.builder()
//                .username(username)
//                .sessionId(sessionId)
//                .build();
//
//
//    }
//
//
//
//
//}
