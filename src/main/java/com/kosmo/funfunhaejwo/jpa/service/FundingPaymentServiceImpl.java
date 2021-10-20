package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.payment.vo.ReceivePaymentVo;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.ProductPayment;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Address;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ProductPaymentStatus;
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
@Slf4j
@RequiredArgsConstructor
public class FundingPaymentServiceImpl implements FundingPaymentService {

    private final FundingRepo fundingRepo;
    private final ProductPaymentRepo productPaymentRepo;

    @Override
    public ResponseEntity<?> saveAccountPayment(Long funding_id, String real_name, String account) {
        Funding findFunding = fundingRepo.findFundingById(funding_id).orElseThrow(() -> new UsernameNotFoundException("등록된 펀딩이 없습니다."));
        ProductPayment saveProductPayment = productPaymentRepo.findByFunding(findFunding).orElse(null);
        if (saveProductPayment != null) {
            return ResponseEntity.status(408).body(false);
        }
        saveProductPayment = ProductPayment.builder()
                .funding(findFunding)
                .account(account)
                .real_name(real_name)
                .productPaymentStatus(ProductPaymentStatus.WAITING)
                .w_product("NOTNEED")
                .build();

        productPaymentRepo.save(saveProductPayment);
        return ResponseEntity.ok().body(true);
    }

    @Override
    public ResponseEntity<?> saveDeliveryPayment(ReceivePaymentVo receivePaymentVo) {
        log.info(receivePaymentVo.toString());
        Funding findFunding = fundingRepo.findFundingById(receivePaymentVo.getFunding_id()).orElseThrow(() -> new UsernameNotFoundException("등록된 펀딩이 없습니다."));
        ProductPayment saveProductPayment = productPaymentRepo.findByFunding(findFunding).orElse(null);
        if (saveProductPayment != null) {
            return ResponseEntity.status(408).body(false);
        }
        saveProductPayment = ProductPayment.builder()
                .funding(findFunding)
                .product(findFunding.getProduct())
                .productPaymentStatus(ProductPaymentStatus.WAITING)
                .excess_cost(receivePaymentVo.getExcess_cost())
                .w_product("NEED")
                .account(receivePaymentVo.getAccount())
                .phone_number(receivePaymentVo.getPhone_number())
                .address(Address.builder()
                        .city(receivePaymentVo.getCity())
                        .street(receivePaymentVo.getStreet())
                        .zipcode(receivePaymentVo.getZipcode())
                        .build())
                .real_name(receivePaymentVo.getReal_name())
                .build();
        productPaymentRepo.save(saveProductPayment);
        return ResponseEntity.ok().body(true);
    }
}
