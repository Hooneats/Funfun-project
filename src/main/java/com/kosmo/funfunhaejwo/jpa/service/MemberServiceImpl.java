package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member saveMember(Member member) {
        log.info("Save Member, Member nic_name is {}", member.getNic_name());
        if (member.getPassword() != null) {
            String encodePassword = passwordEncoder.encode(member.getPassword());
            member.setPasswordEncoding(encodePassword);
        }
        return memberRepo.save(member);
    }

    @Override
    public List<Member> saveAllMemberList(List<Member> memberList) {
        log.info("Save All MemberList");
        List<Member> memberList1 = memberRepo.saveAll(memberList);
        return memberList1;
    }

    @Override
    public Member getMemberByEmail(String email) {
        log.info("Will find Member email {}",email);
        return memberRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
//        return memberRepo.findByEmail(email).orElse(null);
    }

}
