package com.kosmo.funfunhaejwo.jpa.controller.main;

import com.kosmo.funfunhaejwo.jpa.domain.FriendListTable;
import com.kosmo.funfunhaejwo.jpa.service.MainService;
import com.kosmo.funfunhaejwo.jpa.vo.Main_FriendVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainDeadlineVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainJoinVo;
import com.kosmo.funfunhaejwo.jpa.vo.Main_mainSearchVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mainPage")
@RequiredArgsConstructor
public class MainPageController {
    private final MainService mainservice;

//    @GetMapping("/test/{member}/{searchName}")
//    List<Main_FriendVo> test(@PathVariable("member") long member_id, @PathVariable("searchName") String searchName){
//        System.out.println("#여기까진 싱행됨 member_id:" + member_id);
//        List<Main_FriendVo> list = mainservice.getFriendList(member_id,searchName);
//        return list;
//    }

    @GetMapping("/{member}/{searchName}")
    List<Main_FriendVo> getFriendList(@PathVariable("member") long member_id, @PathVariable("searchName") String searchName){
        List<Main_FriendVo> list = mainservice.getFriendList(member_id,searchName);
        return list;
    }

    @GetMapping("/friend/{member}")
    List<Main_mainSearchVo> getMainSearch(@PathVariable("member") long member_id){
        System.out.println("#member_id = " + member_id);
        List<Main_mainSearchVo> list = mainservice.getMainSearchList(member_id);
        return list;
    }

    @GetMapping("/Deadline")
    List<Main_mainDeadlineVo> getDeadline(){
        List<Main_mainDeadlineVo> list = mainservice.getDeadlineList();
        System.out.println("list = " + list);
        return list;
    }

    @GetMapping("/mainJoin/{member}")
    List<Main_mainJoinVo> getMainJoin(@PathVariable("member") long member_id){
        List<Main_mainJoinVo> list = mainservice.getJoinList(member_id);
        return list;
    }
}
