package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.vo.BPay_productInfoVo;
import com.kosmo.funfunhaejwo.jpa.vo.BPay_setPayInfoVo;

public interface BuyPayService {
    BPay_productInfoVo getProductS(long productId, long memberId);
    BPay_setPayInfoVo setPayS(long productId, long memberId);
    //Orders에 success update
    boolean updatePayS(long productId, long orderId, long amount, String imp_uid, String pg_id);
    //Orders에 fail update
    void updateFailS(long orderId);
}
