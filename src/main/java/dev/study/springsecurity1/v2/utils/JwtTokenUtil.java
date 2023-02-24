package dev.study.springsecurity1.v2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {

    /**
     * JWT 토큰 정보 출력하기  ( 유저 정보 )
     * @param token token
     * @param secretKey secretKey
     * @return String
     */
    public static String getUserName(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    /**
     * 토큰 만료 시간 검증
     * @param token token
     * @param secretKey secretKey
     * @return
     */
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }


    public static String createToken(String userName, String key, long expireTimes) {

        // 일종의 map 넣고 싶은 정보들을 Claims 에 넣으면 된다.
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 만든 날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimes)) // 만료 날짜 ( 만료 시간을 추가로 넣어줘야한다. )
                .signWith(SignatureAlgorithm.HS256, key) // 해당 키값으로 설정 알고리즘을 사용해서 암호화를 진행
                .compact();

    }


}
