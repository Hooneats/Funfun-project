package com.kosmo.funfunhaejwo.jpa.domain;

import com.kosmo.funfunhaejwo.jpa.domain.semi.Address;
import com.kosmo.funfunhaejwo.jpa.domain.semi.BaseTime;
import com.kosmo.funfunhaejwo.jpa.domain.semi.LoginApi;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Role;
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

    //좋아요랑 프렌드 리스트, 프로필이미지 는 조회할때 편하도록
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private ProfileImg profile_img;

    @OneToMany(mappedBy = "member")
    private List<Like> likes;


}
