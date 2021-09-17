package com.kosmo.funfunhaejwo.jpa.domain;

import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.domain.semi.LoginApi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@SequenceGenerator(
        name = "PROFILE_IMG_SEQ_GENERATOR",
        sequenceName = "PROFILE_IMG_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class ProfileImg {

    @Id
    @Column(name = "profile_img_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_IMG_SEQ_GENERATOR")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Embedded
    private File_info file_info;

    public ProfileImg settingMember(Member member) {
        this.member = member;
        return this;
    }

    public ProfileImg modifyFileInfo(File_info file_info) {
        this.file_info = file_info;
        return this;
    }

//    @Enumerated(EnumType.STRING)
//    @Column(name = "login_api")
//    private LoginApi loginApi;

}
