package com.kosmo.funfunhaejwo.jpa.controller;

import com.kosmo.funfunhaejwo.jpa.domain.FriendListTable;
import com.kosmo.funfunhaejwo.jpa.service.MainService;
import com.kosmo.funfunhaejwo.jpa.vo.Main_FriendVo;
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

    @GetMapping("/test/{member}/{searchName}")
    List<String> test(@PathVariable("member") long member_id, @PathVariable("searchName") String searchName){
        System.out.println("#여기까진 싱행됨 member_id:" + member_id);
        List<FriendListTable> list = mainservice.getTest(member_id,searchName);
        List<String> ids=new ArrayList<>();
        for(FriendListTable a:list){
            ids.add(a.getFriend().getNic_name());
        }
        return ids;
    }
}
