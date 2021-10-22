package com.kosmo.funfunhaejwo.jpa.controller.funding;

import com.kosmo.funfunhaejwo.jpa.domain.semi.ProductPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnFundingInfo{
    private Long funding_id;
    private String funding_title;
    private LocalDateTime funding_create_time;
    private LocalDateTime funding_expired_time;
    private Long member_id;
    private Long product_id;
    private Long funding_target_money;
    private List<String> fundingImg;
    private int funding_people_count;
    private Long funding_collected_money;
    private String funding_product_brand;
    private Long funding_beetweenTime;
    private Boolean funding_isStart;
    private Long funding_beforeStartDays;
    private String member_nicname;
    private String  funding_status;
    private int delivery_num;

}