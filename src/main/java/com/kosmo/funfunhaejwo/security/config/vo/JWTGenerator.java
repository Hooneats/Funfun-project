package com.kosmo.funfunhaejwo.security.config.vo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTGenerator {
/**
 * HAMC 보안비밀키
 * */

    public static String create_access_JWT(String name, String requestUrl, Collection<GrantedAuthority> authorities, int min) {
        Algorithm algorithm = Algorithm.HMAC256("KosmoFunFunHaeJow".getBytes());

        String access_token = JWT.create()
                .withSubject(name)
                .withExpiresAt(new Date(System.currentTimeMillis() + min * 60 * 1000)) // 10분
                .withIssuer(requestUrl)
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        return access_token;
    }

    public static String create_refresh_JWT(String name, String requestUrl, int min) {
        Algorithm algorithm = Algorithm.HMAC256("KosmoFunFunHaeJow".getBytes());

        String refresh_token = JWT.create()
                .withSubject(name)
                .withExpiresAt(new Date(System.currentTimeMillis() + min * 60 * 1000)) // 30분
                .withIssuer(requestUrl)
                .sign(algorithm);
        return refresh_token;
    }

}
