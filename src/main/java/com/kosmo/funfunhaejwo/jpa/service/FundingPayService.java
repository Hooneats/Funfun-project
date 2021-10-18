package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.vo.FPay_fundingInfoVo;
import com.kosmo.funfunhaejwo.jpa.vo.FPay_setPayInfoVo;

public interface FundingPayService {
    //결제할 펀딩 정보
    FPay_fundingInfoVo getFuningS(long fundingId);
    //Orders에 셋팅 + merchant_uid 생성
    FPay_setPayInfoVo setPayS(long fundingId, long memberId);
    //Orders에 success update
    boolean updatePayS(long fundingId, long orderId, long amount, String imp_uid, String pg_id);
    //Orders에 fail update
    void updateFailS(long orderId);
}
