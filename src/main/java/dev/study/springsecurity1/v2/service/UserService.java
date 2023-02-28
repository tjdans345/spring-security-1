package dev.study.springsecurity1.v2.service;

import dev.study.springsecurity1.v2.domain.entity.User;
import dev.study.springsecurity1.v2.exception.AppException;
import dev.study.springsecurity1.v2.exception.ErrorCode;
import dev.study.springsecurity1.v2.repository.UserRepository;
import dev.study.springsecurity1.v2.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.token.secret}")
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60L;


    /**
     * 회원 가입
     * @param userName userName
     * @param password password
     * @return 성공 여부 , Error 발생시 Exception
     */
    public String join(String userName, String password) {

        // username 중복 check
        userRepository.findByUserName(userName)
                // 체이닝으로 가능하다.
                .ifPresent(user -> {
                            throw new AppException(ErrorCode.USERNAME_DUPLICATED, userName + "은 이미 있습니다.");
                });

         // 저장
        userRepository.save(User
                .builder()
                .userName(userName)
                // 추 후 평문 -> 암호문으로 코드 수정할 것
                .password(bCryptPasswordEncoder.encode(password))
                .build()
        );
        return "SUCCESS";
    }


    public String login(String userName, String password) {

        // UserName 없음
//        User selectedUser = userRepository.findByUserName(userName).orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));
//
//        // password 틀림
//        // bCryptPasswordEncoder 를 이용해서 패스워드 매칭 검증
//        if(!bCryptPasswordEncoder.matches(password, selectedUser.getPassword())) {
//            throw new AppException(ErrorCode.USER_PASSWORD_INVALID, "패스워드가 일치하지 않습니다.");
//        }

        // 이 코드는 의존성이 강한 코드라고 느껴진다.
        // 서비스에 JwtTokenUtil 에서 필요한 값들이 많이 의존적임
        // 필요한 값은 JwtTokenUtil 에 세팅하는게 좋을 것 같음

//        return JwtTokenUtil.createToken(selectedUser.getUserName(), key, expireTimeMs);
        return JwtTokenUtil.createToken("meteor3", key, expireTimeMs);

    }

}
