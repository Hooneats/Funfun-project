package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            member.setPasswordEncoded(encodePassword);
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
        return memberRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일로 회원을 찾을 수 없습니다."));
//        return memberRepo.findByEmail(email).orElse(null);
    }

    @Override
    public List<String> findAllbyPhone(String phone) {
        List<Member> list = new ArrayList<>();
        list = memberRepo.findByPhone_number(phone);
        if (list.isEmpty()) {
            throw new UsernameNotFoundException("전화번호로 찾을 수 있는 회원 아이디가 없습니다.");
        }
        List<String> memberEmailList = list.stream().map(Member::getEmail).collect(Collectors.toList());
        return memberEmailList;
    }

}
