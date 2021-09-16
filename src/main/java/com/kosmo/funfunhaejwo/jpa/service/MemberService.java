package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Member;

import java.util.List;

public interface MemberService {

    Member saveMember(Member member);

    List<Member> saveAllMemberList(List<Member> memberList);

    Member getMember(String email);

}
