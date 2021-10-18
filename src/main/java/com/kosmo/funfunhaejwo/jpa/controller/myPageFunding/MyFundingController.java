package com.kosmo.funfunhaejwo.jpa.controller.myPageFunding;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.service.MyFundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("myFunding")
public class MyFundingController {
    private final MyFundingService myFundingService;

    @GetMapping("/{member}")
    ResponseEntity<List<ReturnFundingInfo>> getFundingByMember(@PathVariable("member") long member_id){
        List<Funding> fundingListByMember = myFundingService.findFundingByMember(member_id);
        log.info("member_id is : {}", member_id);
        log.info("funding is : {}", fundingListByMember);

        ResponseEntity<List<ReturnFundingInfo>> returnFundingInfos = myFundingService.getFundingById(fundingListByMember);
        return returnFundingInfos;
    }
}
