package dev.study.springsecurity1.v2.service;

import dev.study.springsecurity1.v2.domain.entity.User;
import dev.study.springsecurity1.v2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public String join(String userName, String password) {

        // username 중복 check
        userRepository.findByUserName(userName)
                // 체이닝으로 가능하다.
                .ifPresent(user -> {
                            throw new RuntimeException(userName + "는 이미 있습니다.");
                        }
                );

        // 저장
        userRepository.save(User
                .builder()
                .userName(userName)
                // 추 후 평문 -> 암호문으로 코드 수정할 것
                .password(password)
                .build()
        );

        return "SUCCESS";
    }


}
