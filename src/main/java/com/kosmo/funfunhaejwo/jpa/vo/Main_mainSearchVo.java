package com.kosmo.funfunhaejwo.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Main_mainSearchVo {
    private long fundingId;
    private String fundingTitle;
    private String fundingBrand;
    private long fundingTargetMoney;
    private String fundingUrl;
}
