package com.kosmo.funfunhaejwo.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FPay_fundingInfoVo {
    private String psImg;   //productSub1Img
    private String fTitle;
    private long fcMoney;
    private long ftMoney;
    private String btel;
}
