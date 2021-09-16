package com.kosmo.funfunhaejwo.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import com.kosmo.funfunhaejwo.security.config.dao.CrossHeader;
import com.kosmo.funfunhaejwo.security.config.dao.JWTGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
//    private final MemberRepo memberRepo;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
//        this.memberRepo = memberRepo;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("# Member name is {}", username);
        log.info("# Password is {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
//        Algorithm algorithm = Algorithm.HMAC256("funfun".getBytes());

        String access_token = JWTGenerator.create_access_JWT(user.getUsername(), request.getRequestURL().toString(), user.getAuthorities(), 10);
        String refresh_token = JWTGenerator.create_refresh_JWT(user.getUsername(), request.getRequestURL().toString(), 30);

//        log.info("# 유저 이메일 : "+user.getUsername());
//        Member member = memberRepo.findByEmail(user.getUsername()).orElseThrow(()->new UsernameNotFoundException("유저가 없습니다."));

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);

//        여기서 넣어서 보내기보단 토큰을 주고 토큰을 받으면 토큰으로 요청하는 형식이 맞는 것 같다.
//        tokens.put("member_id", String.valueOf(member.getId()));
//        tokens.put("member_email", member.getEmail());
//        tokens.put("member_nic_name", member.getNic_name());

        response = CrossHeader.corsHeader(response);

//        response.setHeader("Access-Control-Allow-Origin", "*");     //허용할 Origin(요청 url) : "*" 의 경우 모두 허용
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");     //허용할 request http METHOD : POST, GET, DELETE, PUT
//        response.setHeader("Access-Control-Max-Age", "3600");     //브라우저 캐시 시간(단위: 초) : "3600" 이면 최소 1시간 안에는 서버로 재요청 되지 않음
//        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");    //요청 허용 헤더(ajax : X-Requested-With)
//
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        // 사용자에게 전송
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }
}
