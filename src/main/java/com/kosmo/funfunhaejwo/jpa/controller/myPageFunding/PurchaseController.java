package com.kosmo.funfunhaejwo.jpa.controller.myPageFunding;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("myPurchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/selfGift/{member}")
    ResponseEntity<?> getFundingTypeBuy(@PathVariable("member") Long member_id){
        return purchaseService.getFundingTypeBuy(member_id);
    }

    @GetMapping("/expiredFunding/{member}")
    ResponseEntity<?> getFundingTypeFunding(@PathVariable("member") Long member_id){
        return purchaseService.getFundingTypeFunding(member_id);
    }
}
