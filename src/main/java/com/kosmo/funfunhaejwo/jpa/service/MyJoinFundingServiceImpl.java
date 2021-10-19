package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.funding.ReturnFundingInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.Order;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.OrderRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductRepo;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class MyJoinFundingServiceImpl implements MyJoinFundingService{
    private final OrderRepo orderRepo;
    private final FundingRepo fundingRepo;
    private final ProductImgService productImgService;

    @Override
    public List<Order> findOrderByMember_id(Long member_id){
        log.info("Find Funding by member_Id {}",member_id);
        return orderRepo.findByMember_id(member_id);
    }

    @Override
    public ResponseEntity<List<ReturnFundingInfo>> getFundingFromOrder(List<Order> orderList){

        List<Long> orderFromFundingList = orderList.stream().map(order -> {
            return order.getFunding().getId();
        }).collect(Collectors.toList());

        log.info("here!",orderFromFundingList);

        Funding findFunding = new Funding();
        ReturnFundingInfo returnFundingInfo = new ReturnFundingInfo();
        List<ReturnFundingInfo> returnFundingInfoList = new ArrayList<>();



        for(long i : orderFromFundingList){
            System.out.println("##i = " + i);
            findFunding = fundingRepo.findById(i).orElseThrow(()-> new UsernameNotFoundException("펀딩을 찾을 수 없어요"));
            List<ProductImg> productImgList = new ArrayList<>();
            productImgList = productImgService.getProductImgByProduct(findFunding.getProduct());
            List<String> productImgStringList = productImgList.stream().map(productImg -> {
                String imgUrl="http://127.0.0.1:8887/";
                String imgUrlDetail=productImg.getFile_info().getFile_src();
                return imgUrl+imgUrlDetail;

            }).collect(Collectors.toList());

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
                    .build();

            log.info(returnFundingInfo.toString());

            returnFundingInfoList.add(returnFundingInfo);
        }


        return ResponseEntity.ok().body(returnFundingInfoList);

    }

}
