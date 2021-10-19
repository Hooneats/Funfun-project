package com.kosmo.funfunhaejwo.jpa.domain.semi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductPaymentStatus {

    CHECKING("펀딩완료확인을 회원이 확인후 정보입력필요"),
    WAITING("관리자 확인필요"),
    PROCESSING("관리자확인"),
    DEPOSIT_COMPLETED_SHIPPING("입금완료 및 배송중"),
    SHIPPING("배송중"),
    COMPLETED("최종완료");

    private final String detail;


}
