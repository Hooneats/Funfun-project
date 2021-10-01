package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.FriendListTable;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.vo.Main_FriendVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainDeadlineVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainJoinVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainSearchVo;

import java.util.List;

public interface MainService {
    //test
    List<FriendListTable> getTest(long member_id, String searchName);
    //멤버id,searchName->친구리스트
    List<Main_FriendVo> getFriendList(long member_id, String searchName);
    //멤버id(친구꺼) -> 펀딩리스트
    List<Main_mainSearchVo> getMainSearchList(long member_id);
    //void -> 마감예정인 펀딩리스트
    List<Main_mainDeadlineVo> getDeadlineList();
    //멤버id -> 내가 참여한 선물(펀딩)리스트
    List<Main_mainJoinVo> getJoinList(long member_id);
}
