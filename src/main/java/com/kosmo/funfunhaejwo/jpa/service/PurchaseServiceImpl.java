package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.*;
import com.kosmo.funfunhaejwo.jpa.domain.semi.FundingType;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ProductPaymentStatus;
import com.kosmo.funfunhaejwo.jpa.repository.DeliveryRepo;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductPaymentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kosmo.funfunhaejwo.jpa.domain.semi.ProductPaymentStatus.CHECKING;
import static com.kosmo.funfunhaejwo.jpa.domain.semi.ProductPaymentStatus.WAITING;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{
    private final FundingRepo fundingRepo;
    private final ProductImgService productImgService;
    private final ProductPaymentRepo productPaymentRepo;
    private final DeliveryRepo deliveryRepo;

    @Override
    public List<Funding> getFundingByMember(Long member_id){
        log.info("Find Funding by member_Id {}",member_id);
        return fundingRepo.findByMember_id(member_id);
    }

    @Override
    public ResponseEntity<List<ReturnFundingInfo>> getFundingTypeBuy(Long member_id) {
        List<Funding> fundingList = getFundingByMember(member_id);
        List<Long> fundingByMemberBuy = new ArrayList<>();
        fundingList.stream().forEach(funding->{
            if (funding.getFunding_type() == FundingType.BUY) {
                fundingByMemberBuy.add(funding.getId());
            }
        });
//        System.out.println("getFundingTypeBuy무한루프 2");
        Funding findFunding = new Funding();
        ReturnFundingInfo returnFundingInfo = new ReturnFundingInfo();
        List<ReturnFundingInfo> returnFundingInfoList = new ArrayList<>();
//        System.out.println("getFundingTypeBuy무한루프 3");
        for (long i : fundingByMemberBuy) {
            System.out.println("##i = " + i);
            findFunding = fundingRepo.findById(i).orElseThrow(() -> new UsernameNotFoundException("펀딩을 찾을 수 없어요"));
            List<ProductImg> productImgList = new ArrayList<>();
            productImgList = productImgService.getProductImgByProduct(findFunding.getProduct());
            List<String> productImgStringList = productImgList.stream().map(productImg -> {
                String imgUrl = "http://127.0.0.1:8887/";
                String imgUrlDetail = productImg.getFile_info().getFile_src();
                return imgUrl + imgUrlDetail;

            }).collect(Collectors.toList());

            String status = "";
            ProductPayment productPayment = productPaymentRepo.getProductPaymentByFunding(i).orElse(null);
            if (productPayment == null) {
                status = WAITING.getStringStatus();
            } else {
                status = productPayment.getProductPaymentStatus().getStringStatus();
            }

            LocalDateTime funding_created_time = findFunding.getFunding_create_time();
            LocalDateTime funding_expired_time = findFunding.getFunding_expired_time();
            Long betweenTime = 0L;

            if (Duration.between(LocalDateTime.now(), funding_created_time).toDays() < 0) {
                betweenTime = Duration.between(LocalDateTime.now(), funding_expired_time).toDays();
            } else {
                betweenTime = Duration.between(funding_created_time, funding_expired_time).toDays();
            }
            Long beforeStartFunding = 0L;
            Boolean isStart = true;
            if (Duration.between(funding_created_time, LocalDateTime.now()).toDays() < 0) {
                isStart = false;
                beforeStartFunding = Duration.between(LocalDateTime.now(), funding_created_time).toDays();
            }


            returnFundingInfo = ReturnFundingInfo.builder()
                    .funding_id(findFunding.getId())
                    .funding_create_time(findFunding.getFunding_create_time())
                    .funding_expired_time(findFunding.getFunding_expired_time())
                    .funding_target_money(findFunding.getFunding_target_money())
                    .funding_title(findFunding.getFunding_title())
                    .member_nicname(findFunding.getMember().getNic_name())
                    .product_id(findFunding.getProduct().getId())
                    .fundingImg(productImgStringList)
                    .funding_people_count(findFunding.getFunding_people_count())
                    .funding_collected_money(findFunding.getFunding_collected_money())
                    .funding_product_brand(findFunding.getProduct().getProduct_brand())
                    .funding_beetweenTime(betweenTime)
                    .funding_isStart(isStart)
                    .funding_beforeStartDays(beforeStartFunding)
                    .funding_status(status)
                    .build();

            log.info(returnFundingInfo.toString());


            returnFundingInfoList.add(returnFundingInfo);
        }

//        System.out.println("getFundingTypeBuy무한루프 4");

        return ResponseEntity.ok().body(returnFundingInfoList);

    }

    @Override
    public ResponseEntity<List<ReturnFundingInfo>> getFundingTypeFunding(Long member_id){
//        System.out.println("getFundingTypeFunding무한루프 1");
        List<Funding> fundingList = getFundingByMember(member_id);
        List<Long> fundingByMemberFunding = new ArrayList<>();
        fundingList.stream().forEach(funding->{
            if (funding.getFunding_type() == FundingType.FUNDING) {
                fundingByMemberFunding.add(funding.getId());
            }
        });
//        System.out.println("getFundingTypeFunding무한루프 2");
        Funding findFunding = new Funding();
        ReturnFundingInfo returnFundingInfo = new ReturnFundingInfo();
        List<ReturnFundingInfo> returnFundingInfoList = new ArrayList<>();
//        System.out.println("getFundingTypeFunding무한루프3");
        for(long i : fundingByMemberFunding){
            System.out.println("#i = " + i);
            findFunding = fundingRepo.findById(i).orElseThrow(()-> new UsernameNotFoundException("펀딩을 찾을 수 없어요"));
            List<ProductImg> productImgList = new ArrayList<>();
            productImgList = productImgService.getProductImgByProduct(findFunding.getProduct());
            List<String> productImgStringList = productImgList.stream().map(productImg -> {
                String imgUrl="http://127.0.0.1:8887/";
                String imgUrlDetail=productImg.getFile_info().getFile_src();
                return imgUrl+imgUrlDetail;

            }).collect(Collectors.toList());

            String status = "";
            ProductPayment productPayment = productPaymentRepo.getProductPaymentByFunding(i).orElse(null);
            if (productPayment == null) {
                status = CHECKING.getStringStatus();
            } else {
                status = productPayment.getProductPaymentStatus().getStringStatus();
            }

            int delivery_number=0;

            Delivery delivery = deliveryRepo.findDeliveryByProductPayment(productPayment);
            if(delivery == null){
                delivery_number = 0;
            }else{
                delivery_number = delivery.getDelivery_number();
            }


//            System.out.println("getFundingTypeFunding무한루프 4");
            LocalDateTime funding_created_time = findFunding.getFunding_create_time();
            LocalDateTime funding_expired_time = findFunding.getFunding_expired_time();
            Long betweenTime=0L;

            if(Duration.between(LocalDateTime.now(),funding_created_time).toDays()<0){
                betweenTime = Duration.between(LocalDateTime.now(),funding_expired_time).toDays();
            }else{
                betweenTime = Duration.between(funding_created_time,funding_expired_time).toDays();
            }
            Long beforeStartFunding = 0L;
            Boolean isStart = true;
            if(Duration.between(funding_created_time,LocalDateTime.now()).toDays()<0){
                isStart = false;
                beforeStartFunding = Duration.between(LocalDateTime.now(),funding_created_time).toDays();
            }
//            System.out.println("getFundingTypeFunding무한루프 5");

            returnFundingInfo = ReturnFundingInfo.builder()
                    .funding_id(findFunding.getId())
                    .funding_create_time(findFunding.getFunding_create_time())
                    .funding_expired_time(findFunding.getFunding_expired_time())
                    .funding_target_money(findFunding.getFunding_target_money())
                    .funding_title(findFunding.getFunding_title())
                    .member_nicname(findFunding.getMember().getNic_name())
                    .product_id(findFunding.getProduct().getId())
                    .fundingImg(productImgStringList)
                    .funding_people_count(findFunding.getFunding_people_count())
                    .funding_collected_money(findFunding.getFunding_collected_money())
                    .funding_product_brand(findFunding.getProduct().getProduct_brand())
                    .funding_beetweenTime(betweenTime)
                    .funding_isStart(isStart)
                    .funding_beforeStartDays(beforeStartFunding)
                    .funding_status(status)
                    .delivery_num(delivery_number)
                    .build();

            log.info(returnFundingInfo.toString());

            if(Duration.between(LocalDateTime.now(),funding_expired_time).toDays()<0){
                returnFundingInfoList.add(returnFundingInfo);
            }
        }
//        System.out.println("getFundingTypeFunding무한루프 6");
        return ResponseEntity.ok().body(returnFundingInfoList);
    }
}
