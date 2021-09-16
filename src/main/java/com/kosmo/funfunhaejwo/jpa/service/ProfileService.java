package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;

public interface ProfileService {

    ProfileImg saveProfile(ProfileImg profileImg);

    ProfileImg getMember(Member member);
}
