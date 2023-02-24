package dev.study.springsecurity1.v2.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// JWT 필터 적용
@RestController
@RequestMapping("/api/v2/users")
public class UserController2 {


    @PostMapping("/login")
    public ResponseEntity<String> login() {

        return ResponseEntity.status(HttpStatus.OK).body("token");
    }


}
