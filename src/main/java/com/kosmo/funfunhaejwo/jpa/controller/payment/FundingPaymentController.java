package com.kosmo.funfunhaejwo.jpa.controller.payment;

import com.kosmo.funfunhaejwo.jpa.controller.payment.vo.ReceivePaymentVo;
import com.kosmo.funfunhaejwo.jpa.exception.ReturnExceptionResponse;
import com.kosmo.funfunhaejwo.jpa.service.FundingPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/post/payment")
public class FundingPaymentController {
    private final FundingPaymentService fundingPaymentService;

    @PostMapping("/account")
    public ResponseEntity<?> savePaymentWithAccount(Long funding_id, String real_name, String account) {
        return fundingPaymentService.saveAccountPayment(funding_id, real_name, account);
    }

    @PostMapping("/delivery")
    public ResponseEntity<?> savePaymentWithDelivery(@RequestBody ReceivePaymentVo receivePaymentVo) {
        return fundingPaymentService.saveDeliveryPayment(receivePaymentVo);
    }
}
