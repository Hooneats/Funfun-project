package com.kosmo.funfunhaejwo.security.config.dao;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

public class CrossHeader {

    public static HttpServletResponse corsHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");     //허용할 Origin(요청 url) : "*" 의 경우 모두 허용
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");     //허용할 request http METHOD : POST, GET, DELETE, PUT
        response.setHeader("Access-Control-Max-Age", "3600");     //브라우저 캐시 시간(단위: 초) : "3600" 이면 최소 1시간 안에는 서버로 재요청 되지 않음
        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");    //요청 허용 헤더(ajax : X-Requested-With)

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        return response;
    }

}
