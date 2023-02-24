package dev.study.springsecurity1.v2.filter;

import dev.study.springsecurity1.v2.service.UserService;
import dev.study.springsecurity1.v2.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
// 매번 인증을 하기 위해 OncePerRequestFilter 상속 받는다.
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final String secretKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader("Authorization");

        // 토큰이 없다면
        if(!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer")) {
            log.error("authorization 을 잘못 보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        log.info(" authorization : ??? {} ", authorization);

        // Token 꺼내기
        String token = authorization.substring(7);
        log.info(" token : ??? {} ", token);

        // Token Expired 확인
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            log.error("토큰이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // UserName 을 Token 에서 꺼내기
        String userName = "";

        // 권한 부여
        // credentials 는 필요하면 넣으면된다.
        // 3번째 파라미터는 유저디테일이다 ( 유저 권한등을 넣을 수 있다. )
        // new SimpleGrantedAuthority("USER")를 사용해서 하드코딩을 할 수 있다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 시큐리티 세션영역안에 시큐리티 인증 객체 정보를 넣어준다.
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);




    }
}
