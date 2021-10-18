package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MyJoinFundingService {
   List<Order> findOrderByMember_id(Long member_id);

   ResponseEntity<List<ReturnFundingInfo>> getFundingFromOrder(List<Order> orderList);
}
