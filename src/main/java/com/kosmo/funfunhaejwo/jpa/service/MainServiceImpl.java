package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.FriendListTable;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.repository.FriendListTableRepo;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import com.kosmo.funfunhaejwo.jpa.repository.OrderRepo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_FriendVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainDeadlineVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainJoinVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainSearchVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{
    private final FriendListTableRepo friendListTableRepo;
    private final FundingRepo fundingRepo;
    private final MemberRepo memberRepo;
    private final OrderRepo orderRepo;

    @Override
    public List<FriendListTable> getTest(long member_id, String searchName) {
        System.out.println("#여기까진 싱행됨2");

        List<FriendListTable> friendIdList = null;
        if(searchName == null){
            friendIdList = friendListTableRepo.findByMember_id(new Long(member_id));
        }else {
            friendIdList = friendListTableRepo.friendSearch(member_id,searchName);
        }

        return friendIdList;
    }

    @Override
    public List<Main_FriendVo> getFriendList(long member_id, String searchName) {
        List<Main_FriendVo> list = new ArrayList<Main_FriendVo>();

        List<FriendListTable> friendIdList = null;
        if(searchName == null){
            friendIdList = friendListTableRepo.findByMember_id(new Long(member_id));
        }else {
            friendIdList = friendListTableRepo.friendSearch(member_id,searchName);
        }




        return list;
    }

    @Override
    public List<Main_mainSearchVo> getMainSearchList(long member_id) {
        return null;
    }

    @Override
    public List<Main_mainDeadlineVo> getDeadlineList() {
        return null;
    }

    @Override
    public List<Main_mainJoinVo> getJoinList(long member_id) {
        return null;
    }
}
