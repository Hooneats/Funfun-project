package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.payment.vo.ReceivePaymentVo;
import com.kosmo.funfunhaejwo.jpa.domain.ProductPayment;
import org.springframework.http.ResponseEntity;

public interface FundingPaymentService {

    ResponseEntity<?> saveAccountPayment(Long funding_id, String real_name, String account);

    ResponseEntity<?> saveDeliveryPayment(ReceivePaymentVo receivePaymentVo);

}
