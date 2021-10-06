package com.kosmo.funfunhaejwo.jpa.domain;

import com.kosmo.funfunhaejwo.jpa.domain.semi.*;
import com.kosmo.funfunhaejwo.jpa.exception.BadRequestLoginApiException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 객체지향 패러다임 불일치 해결
 * */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "member_uk",
        columnNames = "email")})
public class Member extends BaseTime {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column( nullable = false)
    private String email;

    private String nic_name;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_api", length = 30)
    private LoginApi login_api;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private Role role;

    private String  phone_number;

    @Embedded
    private Address address;

//    //좋아요랑 프렌드 리스트, 프로필이미지 는 조회할때 편하도록
//    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
//    private ProfileImg profile_img;

    @OneToMany(mappedBy = "member")
    private List<Like> likes;

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }
    //인코딩된 패스워드를 넣어줘야 한다.
    public Member setPasswordEncoded(String password) {
        this.password = password;
        return this;
    }

    public Member setLoginApiSwitch(String login_api) {
        if (login_api != null) {
            if (login_api.equals("Kakao")) {
                this.login_api = LoginApi.KAKAO;
            } else if (login_api.equals("Naver")) {
                this.login_api = LoginApi.NAVER;
            } else if (login_api.equals("Google")) {
                this.login_api = LoginApi.GOOGLE;
            } else {
                throw new BadRequestLoginApiException("잘못된 로그인 API 요청입니다.");
            }
        }
        return this;
    }


    public void settingNic_name(String nic_name) {
        this.nic_name = nic_name;
    }
}
