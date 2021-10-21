package com.kosmo.funfunhaejwo.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BPay_productInfoVo {
    private String psImg;   //productSub1Img
    private String pTitle;
    private long pprice;
    private String btel;
}
