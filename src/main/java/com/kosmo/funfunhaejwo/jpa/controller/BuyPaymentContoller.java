package com.kosmo.funfunhaejwo.jpa.controller;

import com.kosmo.funfunhaejwo.jpa.service.BuyPayService;
import com.kosmo.funfunhaejwo.jpa.vo.BPay_productInfoVo;
import com.kosmo.funfunhaejwo.jpa.vo.BPay_setPayInfoVo;
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
@RequestMapping("buyPay")
@RequiredArgsConstructor
public class BuyPaymentContoller {
    private final BuyPayService buyPayService;
    private IamportClient iamportApi;

    void setIamportApi(){
        iamportApi = new IamportClient("7846297502567938","41cd173a75ab599bd4791fa4584154e4890f598a73ed1cfa4a2ba03b7b1791d39a7de80cc3389c48");
    }

    @PostMapping("/productInfo")
    BPay_productInfoVo getProductInfo(@RequestParam("productId") long productId, @RequestParam("memberId") long memberId){
        BPay_productInfoVo productinfo = buyPayService.getProductS(productId,memberId);
        return productinfo;
    }

    @PostMapping("/setPay")
    BPay_setPayInfoVo setPay(@RequestParam("productId") long productId, @RequestParam("memberId") long memberId){
        BPay_setPayInfoVo info = buyPayService.setPayS(productId,memberId);
        return info;
    }

    @PostMapping("/successPay")
    boolean successPay(@RequestParam("productId") long productId,@RequestParam("orderId") long orderId, @RequestParam("amount")long amount, @RequestParam("imp_uid")String imp_uid,@RequestParam("pg_id")String pg_id){
        boolean result = buyPayService.updatePayS(productId, orderId,amount,imp_uid,pg_id);
        return result;
    }
    @PostMapping("/failPay")
    void failPay(@RequestParam("orderId") long orderId){
        buyPayService.updateFailS(orderId);
    }


    @PostMapping("/verifyIamport")
    public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session, @RequestParam("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
//        System.out.println("#verifyIamport");
        if(iamportApi == null)
            setIamportApi();
        return iamportApi.paymentByImpUid(imp_uid);
    }
}
