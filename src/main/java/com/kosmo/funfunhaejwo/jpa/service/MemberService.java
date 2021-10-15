package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.login.vo.ReturnLoginMemberEditInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Member;

import java.util.List;

public interface MemberService {

    Member saveMember(Member member);

    List<Member> saveAllMemberList(List<Member> memberList);

    Member getMemberByEmail(String email);

    List<String > findAllbyPhone(String phone);

    ReturnLoginMemberEditInfo editReturn(Long memberId);

    Boolean editSave(ReturnLoginMemberEditInfo submitEditMember);

    Member saveAndPasswordEncode(String email, String password);



}
