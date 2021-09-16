package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImgRepo  extends JpaRepository<ProfileImg, Long> {
    ProfileImg findByMember(Member member);
}
