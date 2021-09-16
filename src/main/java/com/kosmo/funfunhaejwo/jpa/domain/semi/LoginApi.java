package com.kosmo.funfunhaejwo.jpa.domain.semi;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginApi {

    EMAIL("Email","이메일 계정입니다."),
    GOOGLE("Google","구글 계정입니다."),
    NAVER("Naver", "네이버 계정입니다."),
    KAKAO("Kakao", "카카오 계정입니다.");

    private final String key;
    private final String title;
}
