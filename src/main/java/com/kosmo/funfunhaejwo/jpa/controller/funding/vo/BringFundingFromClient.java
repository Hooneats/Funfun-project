package com.kosmo.funfunhaejwo.jpa.controller.funding.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BringFundingFromClient{
    private Long member_id;
    private Long product_id;
    private String funding_title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime funding_create_time;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime funding_expired_time;
    private String  funding_type;
    private Long funding_target_money;
    private Long funding_collected_money;
}