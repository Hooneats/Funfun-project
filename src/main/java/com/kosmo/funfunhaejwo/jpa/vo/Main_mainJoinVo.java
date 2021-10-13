package com.kosmo.funfunhaejwo.jpa.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Main_mainJoinVo {
    private String preFundingImgUrl;
    private String fundingTitle;
    private int progressBarPercent;
    private String fundingname;
    private long fundingMoney;
    private long fundingId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime expireDate;
}
