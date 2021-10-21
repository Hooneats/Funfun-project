package com.kosmo.funfunhaejwo.jpa.controller;

import com.kosmo.funfunhaejwo.jpa.service.FundingPayService;
import com.kosmo.funfunhaejwo.jpa.vo.FPay_fundingInfoVo;
import com.kosmo.funfunhaejwo.jpa.vo.FPay_setPayInfoVo;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@RestController
@RequestMapping("fundingPay")
@RequiredArgsConstructor
public class FundingPaymentContorller {
    private final FundingPayService fundingpayservice;
    private IamportClient iamportApi;

    void setIamportApi(){
        iamportApi = new IamportClient("7846297502567938","41cd173a75ab599bd4791fa4584154e4890f598a73ed1cfa4a2ba03b7b1791d39a7de80cc3389c48");
    }

    @PostMapping("/fundingInfo")
    FPay_fundingInfoVo getFundingInfo(@RequestParam("fundingId") long fundingId,@RequestParam("memberId") long memberId){
        FPay_fundingInfoVo fundingInfo = fundingpayservice.getFuningS(fundingId, memberId);
        return fundingInfo;
    }

    @PostMapping("/setPay")
    FPay_setPayInfoVo setPay(@RequestParam("fundingId") long fundingId, @RequestParam("memberId") long memberId){
        FPay_setPayInfoVo info = fundingpayservice.setPayS(fundingId,memberId);
        return info;
    }

    @PostMapping("/successPay")
    boolean successPay(@RequestParam("fundingId") long fundingId,@RequestParam("orderId") long orderId, @RequestParam("amount")long amount, @RequestParam("imp_uid")String imp_uid,@RequestParam("pg_id")String pg_id){
        boolean result = fundingpayservice.updatePayS(fundingId, orderId,amount,imp_uid,pg_id);
        return result;
    }
    @PostMapping("/failPay")
    void failPay(@RequestParam("orderId") long orderId){
        fundingpayservice.updateFailS(orderId);
    }



    @PostMapping("/verifyIamport")
    public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session, @RequestParam("imp_uid") String imp_uid)
        throws IamportResponseException, IOException{
        if(iamportApi == null)
            setIamportApi();
        return iamportApi.paymentByImpUid(imp_uid);
    }
}
