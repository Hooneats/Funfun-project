package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.funding.vo.BringFundingFromClient;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.semi.FundingType;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService{
    private final FundingRepo fundingRepo;
    private final ProductRepo productRepo;
    private final MemberRepo memberRepo;

    @Override
    public Funding createFunding(Funding funding){
        log.info("Create Funding, Funding title is {}",funding.getFunding_title());


        return fundingRepo.save(funding);
    }
    @Override
    public Funding getFundingById(Long funding_id){
        log.info("Find Funding by id {}",funding_id);
        return fundingRepo.findFundingById(funding_id).orElseThrow(() -> new UsernameNotFoundException("펀딩을 찾을 수 없습니다."));
    }

    public Long fundingSave(BringFundingFromClient funding) {
        Member findMember = memberRepo.findById(funding.getMember_id()).orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        Product findProduct = productRepo.findById(funding.getProduct_id()).orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        findProduct.fundingControl(true);
        FundingType fundingType = null;
        if (funding.getFunding_type().equals("BUY")) {
            fundingType = FundingType.BUY;
        } else {
            fundingType = FundingType.FUNDING;
        }
        Funding buildFunding = Funding.builder()
                .member(findMember)
                .product(findProduct)
                .funding_title(funding.getFunding_title())
                .funding_target_money(funding.getFunding_target_money())
                .funding_type(fundingType)
                .funding_collected_money(funding.getFunding_collected_money())
                .funding_create_time(funding.getFunding_create_time())
                .funding_expired_time(funding.getFunding_expired_time())
                .build();
        Funding savedFunding = fundingRepo.save(buildFunding);
        return savedFunding.getId();
    }

}
