package com.kosmo.funfunhaejwo.jpa.controller.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminExpiredFundingVo {
    private long data1;
    private String data2;
    private String data3;
    private long data4;
    private long data5;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime data6;
    private String data7;
    private String data9;
}
