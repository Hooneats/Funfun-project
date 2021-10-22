package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaseService {
    List<Funding> getFundingByMember(Long member_id);


    ResponseEntity<List<ReturnFundingInfo>> getFundingTypeBuy(Long member_id);

    ResponseEntity<List<ReturnFundingInfo>> getFundingTypeFunding(Long member_id);
}
