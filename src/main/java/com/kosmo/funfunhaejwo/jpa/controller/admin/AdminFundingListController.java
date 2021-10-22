package com.kosmo.funfunhaejwo.jpa.controller.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminFundingListVo;
import com.kosmo.funfunhaejwo.jpa.service.admin.AdminFundingListService;
import com.kosmo.funfunhaejwo.jpa.service.admin.AdminFundingSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminFundingListController {
    private final AdminFundingListService adminFundingListService;
    private final AdminFundingSearchService adminFundingSearchService;

    @PostMapping("/fundinglist")
    List<AdminFundingListVo> getList() {
        List<AdminFundingListVo> list = adminFundingListService.getFundingList();
        return list;
    }
    @GetMapping("/fundinglist/search/{SearchName}")
    List<AdminFundingListVo> getSearch(@PathVariable("SearchName") String funding_name){
        List<AdminFundingListVo> list = adminFundingSearchService.getFundingList(funding_name);
        return list;
    }
}
