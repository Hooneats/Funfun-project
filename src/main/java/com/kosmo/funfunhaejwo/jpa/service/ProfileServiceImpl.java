package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import com.kosmo.funfunhaejwo.jpa.repository.ProfileImgRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileImgRepo profileImgRepo;
    @Override
    public ProfileImg saveProfile(ProfileImg profileImg) {
        return profileImgRepo.save(profileImg);
    }

    @Override
    public ProfileImg getMember(Member member) {
        return profileImgRepo.findByMember(member);
    }
}
