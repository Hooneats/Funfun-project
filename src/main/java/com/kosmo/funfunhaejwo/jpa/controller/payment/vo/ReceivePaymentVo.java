package com.kosmo.funfunhaejwo.jpa.controller.payment.vo;

import com.kosmo.funfunhaejwo.jpa.domain.semi.Address;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceivePaymentVo {

    private Long funding_id; // 펀딩 아이디
    private int excess_cost; // 초과 금액
    private String account; // 계좌
    private String phone_number; // 전화번호
    private String real_name; // 실명
    private String city; // 주소
    private String street; // 상세주소
    private String zipcode; // 우편번호


}
