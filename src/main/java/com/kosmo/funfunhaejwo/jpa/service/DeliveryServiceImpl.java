package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Delivery;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.ProductPayment;
import com.kosmo.funfunhaejwo.jpa.domain.semi.DeliveryStatus;
import com.kosmo.funfunhaejwo.jpa.repository.DeliveryRepo;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductPaymentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepo deliveryRepo;
    private final FundingRepo fundingRepo;
    private final ProductPaymentRepo productPaymentRepo;

    @Override
    public ResponseEntity<?> insertDelivery(long funding_id, String delivery_num) {
        Funding findFunding = fundingRepo.findFundingById(funding_id).orElseThrow(() -> new UsernameNotFoundException("등록된 펀딩이 없습니다."));
        ProductPayment findProductPayment = productPaymentRepo.findByFunding(findFunding).orElseThrow(() -> new UsernameNotFoundException("등록되어있는 지급정보가 없습니다."));
        Delivery byProductPayment = deliveryRepo.findByProductPayment(findProductPayment);
        if (byProductPayment != null) {
            return null;
        }
        log.info("배송번호 : {}",delivery_num);
        Delivery buildDelivery = Delivery.builder()
                .member(findFunding.getMember())
                .productPayment(findProductPayment)
                .delivery_number(Integer.parseInt(delivery_num))
                .delivery_company("대한통운")
                .phone_number(findProductPayment.getPhone_number())
                .address(findProductPayment.getAddress())
                .status(DeliveryStatus.SHIPPING)
                .build();
        deliveryRepo.save(buildDelivery);
        return ResponseEntity.ok().body(buildDelivery);
    }
}
