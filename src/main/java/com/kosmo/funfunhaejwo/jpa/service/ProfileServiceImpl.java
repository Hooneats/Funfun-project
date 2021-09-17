package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import com.kosmo.funfunhaejwo.jpa.exception.ProfileNotFoundException;
import com.kosmo.funfunhaejwo.jpa.repository.ProfileImgRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileImgRepo profileImgRepo;
    @Override
    public ProfileImg saveProfile(ProfileImg profileImg) {
        log.info("Save ProfileImg, ProfileImg src is {}", profileImg.getFile_info().getFile_src());
        return profileImgRepo.save(profileImg);
    }

    @Override
    public ProfileImg getProfileImgByMember(Member member) {
        log.info("Will find ProfileImg, Member nic_name is {}", member.getNic_name());
        return profileImgRepo.findByMember(member).orElseThrow(() -> new ProfileNotFoundException("# Member does not have ProfileImg"));
    }
}
