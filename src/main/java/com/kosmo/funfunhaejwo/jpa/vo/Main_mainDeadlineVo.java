package com.kosmo.funfunhaejwo.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Main_mainDeadlineVo {
    private String preFundingImgUrl;
    private String fundingTitle;
    private int progressBarPercent;
    private String fundingname;
    private long fundingMoney;
    private long fundingId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp expireDate;
}
