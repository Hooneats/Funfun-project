package com.kosmo.funfunhaejwo.jpa.controller.myPageFunding;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Order;
import com.kosmo.funfunhaejwo.jpa.service.MyJoinFundingService;
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
@RequestMapping("myJoinFunding")
public class MyJoinFundingController {
    private  final MyJoinFundingService myJoinFundingService;

    @GetMapping("/{member}")
    ResponseEntity<List<ReturnFundingInfo>> getFundingFromOrder(@PathVariable("member") long member_id ){
        List<Order> orderListByMember = myJoinFundingService.findOrderByMember_id(member_id);
        log.info("member_id is : {}", member_id);
        log.info("order is :{}",orderListByMember);
        //Order테이블에 member_id로 Order 뽑아오기

        ResponseEntity<List<ReturnFundingInfo>>  returnFundingInfos = myJoinFundingService.getFundingFromOrder(orderListByMember);

        return returnFundingInfos;
    }
}
