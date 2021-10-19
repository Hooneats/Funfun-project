package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MyFundingService {
    List<Funding> findFundingByMember(Long member_id);

    ResponseEntity<List<ReturnFundingInfo>> getFundingById(List<Funding> fundingList);
}
