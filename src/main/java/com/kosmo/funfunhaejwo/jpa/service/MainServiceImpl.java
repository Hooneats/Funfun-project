package com.kosmo.funfunhaejwo.jpa.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kosmo.funfunhaejwo.jpa.domain.FriendListTable;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.Order;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.repository.*;
import com.kosmo.funfunhaejwo.jpa.vo.Main_FriendVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainDeadlineVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainJoinVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainSearchVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{
    private final FriendListTableRepo friendListTableRepo;
    private final FundingRepo fundingRepo;
    private final ProductImgRepo productImgRepo;
    private final MemberRepo memberRepo;
    private final OrderRepo orderRepo;

    @Override
    public List<FriendListTable> getTest(long member_id, String searchName) {
//        System.out.println("#여기까진 싱행됨2");
//
//        List<FriendListTable> friendIdList = null;
//        if(searchName == null){
//            friendIdList = friendListTableRepo.findByMember_id(new Long(member_id));
//        }else {
//            //friendIdList = friendListTableRepo.friendSearch(member_id,searchName);
//        }

//        return friendIdList;
        return null;
    }

    @Override
    public List<Main_FriendVo> getFriendList(long member_id, String searchName) {
        List<Main_FriendVo> list = new ArrayList<Main_FriendVo>();

        List<Object[]> objList =  friendListTableRepo.Search(searchName);
        for (Object[] item : objList) {
            list.add(new Main_FriendVo(FilePath.BASIC_FILE_PATH+(String) item[0],(String) item[1],(String) item[2],Long.parseLong(String.valueOf(item[3]))));
        }

        return list;
    }

    @Override
    public List<Main_mainSearchVo> getMainSearchList(long member_id) {
        List<Main_mainSearchVo> list = new ArrayList<>();


        List<Funding> fundingList = fundingRepo.findByMember_id(member_id);
        for(Funding item: fundingList){
            System.out.println("item = " + item.getId());
            String pthumb = FilePath.BASIC_FILE_PATH + productImgRepo.findThumbByProduct_id(item.getProduct().getId());
            Main_mainSearchVo msv = new Main_mainSearchVo(item.getId(), item.getFunding_title(),item.getProduct().getProduct_brand(),item.getFunding_target_money(),pthumb);
            list.add(msv);
            if(list.size()>=10) break;
        }



        return list;
    }

    @Override
    public List<Main_mainDeadlineVo> getDeadlineList() {
        List<Main_mainDeadlineVo> list = new ArrayList<>();
//        java.util.Date d = new java.util.Date();
//        Date fromDate = new Date(d.getTime());
//        Date toDate = new Date(d.getTime());
//        Calendar c = Calendar.getInstance();
//        c.setTime(toDate);
//        c.add(Calendar.DATE,7);
//        toDate = new Date(c.getTime().getTime());
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime toDate = LocalDateTime.now().plusDays(7L);
//        Pageable pageable = PageRequest.of(1,10, Sort.by("funding_expired_date"));
//        List<Funding> deadlineFundingList = fundingRepo.findAllByFunding_expired_timeBetween(fromDate, toDate, pageable).getContent();
//        for(Funding item: deadlineFundingList){
//            String pthumb = productImgRepo.findThumbByProduct_id(item.getProduct().getId());
//            int progresspercent = (int)((double)item.getFunding_collected_money()/(double)item.getFunding_target_money() * 100.0);
//            Main_mainDeadlineVo mdv = new Main_mainDeadlineVo(pthumb,item.getFunding_title(),progresspercent,item.getMember().getNic_name(),item.getFunding_collected_money(),item.getId(),item.getFunding_expired_time());
//
//            list.add(mdv);
//        }
        System.out.println("#시간: fromDate,toDate -> "+fromDate+" , "+toDate);
        List<Object[]> deadlineFundingList = fundingRepo.findDeadline(fromDate,toDate);
        for(Object[] item: deadlineFundingList){
            String preFundingImgUrl = FilePath.BASIC_FILE_PATH + productImgRepo.findThumbByProduct_id(Long.parseLong(String.valueOf(item[1])));
            String fundingTitle = String.valueOf(item[2]);
            long cMoney = Long.parseLong(String.valueOf(item[4]));
            long tMoney = Long.parseLong(String.valueOf(item[5]));
            int progressBarPercent = (int)((double)cMoney/(double)tMoney * 100.0);
            //max 100 주고 싶은경우 여기에 if문 작성
            String fundingname = String.valueOf(item[6]);
            long fundingMoney = cMoney;
            long fundingId = Long.parseLong(String.valueOf(item[0]));
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String etstr = String.valueOf(item[3]);
//            LocalDateTime expireDate = LocalDateTime.parse(String.valueOf(item[3]),dtf);
            LocalDateTime expireDate = ((Timestamp)item[3]).toLocalDateTime();
            Main_mainDeadlineVo mdv = new Main_mainDeadlineVo(preFundingImgUrl,fundingTitle,progressBarPercent,fundingname,fundingMoney,fundingId,expireDate);

            list.add(mdv);
        }

        return list;
    }

    @Override
    public List<Main_mainJoinVo> getJoinList(long member_id) {
        List<Main_mainJoinVo> list = new ArrayList<>();
        List<Order> orderList = orderRepo.findByMember_id(member_id);
        for(Order order: orderList){
            Funding f = order.getFunding();
            String preFundingImgUrl = FilePath.BASIC_FILE_PATH + productImgRepo.findThumbByProduct_id(f.getProduct().getId());
            String fundingTitle = f.getFunding_title();
            int progressBarPercent = (int)((double)f.getFunding_collected_money()/(double)f.getFunding_target_money() * 100.0);
            System.out.println("###progressBarPercent = " + progressBarPercent);
            String fundingname = f.getMember().getNic_name();
            long fundingMoney = f.getFunding_collected_money();
            long fundingId = f.getId();
            LocalDateTime expireDate = f.getFunding_expired_time();

            Main_mainJoinVo mjv = new Main_mainJoinVo(preFundingImgUrl,fundingTitle,progressBarPercent,fundingname,fundingMoney,fundingId,expireDate);

            list.add(mjv);
        }
        return list;
    }
}
