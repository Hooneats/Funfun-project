package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.login.vo.ReturnLoginMemberEditInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Address;
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
//        if (member.getPassword() != null) {
//            String encodePassword = passwordEncoder.encode(member.getPassword());
//            member.setPasswordEncoded(encodePassword);
//        }
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

    public ReturnLoginMemberEditInfo editReturn(Long memberId) {
        Member member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("찾으시는 회원이 없습니다."));
        String address1 = "";
        String address2 = "";
        String address3 = "";
        String phone = "";

        if (member.getAddress() == null) {
            address1 = "주소를 입력해 주세요.";
            address2 = "상세주소를 입력해 주세요.";
            address3 = "우편번호를 입력해 주세요.";
        } else {
            address1 = member.getAddress().getCity();
            address2 = member.getAddress().getStreet();
            address3 = member.getAddress().getZipcode();
        }
        if (member.getPhone_number() == null) {
            phone = "저장되어있는 번호가 없습니다.";
        } else {
            phone = member.getPhone_number();
        }
        return ReturnLoginMemberEditInfo.builder()
                .nic_name(member.getNic_name())
                .email(member.getEmail())
                .phone_number(member.getPhone_number())
                .city(address1)
                .street(address2)
                .zipcode(address3)
                .build();
    }

    @Override
    public Boolean editSave(ReturnLoginMemberEditInfo submitEditMember) {
        Member findMember = memberRepo.findById(submitEditMember.getId()).orElseThrow(() -> new IllegalArgumentException("찾으시는 회원이 없습니다."));
        String phone = "";
        Address address = new Address();
        if (submitEditMember.getPhone_number() != null) {
            phone = submitEditMember.getPhone_number();
            findMember.setPhoneSave(phone);
        }
        if (submitEditMember.getCity() != null) {
            address.setCity(submitEditMember.getCity());
            address.setStreet(submitEditMember.getStreet());
            address.setZipcode(submitEditMember.getZipcode());
            findMember.setAddressSave(address);
        }
        memberRepo.save(findMember);
        return true;
    }

    @Override
    public Member saveAndPasswordEncode(String email, String password) {
        Member findMember = getMemberByEmail(email);
        findMember.setPasswordEncoded(passwordEncoder.encode(password));
        return memberRepo.save(findMember);
    }

}
