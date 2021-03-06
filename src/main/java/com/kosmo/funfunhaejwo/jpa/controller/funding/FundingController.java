package com.kosmo.funfunhaejwo.jpa.controller.funding;

import com.kosmo.funfunhaejwo.jpa.controller.funding.vo.BringFundingFromClient;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.service.FundingService;
import com.kosmo.funfunhaejwo.jpa.service.ProductImgService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/funding")
public class FundingController {
    private final FundingService fundingService;
    private final ProductImgService productImgService;

    /*@Autowired
    public FundingController(FundingService fundingService){
        this.fundingService = fundingService;
    }*/


    @PostMapping("/fundingDetail")
    public ResponseEntity<ReturnFundingInfo> getFundingDetail(@RequestParam(required = false) Long funding_id,
                                                              HttpServletRequest request) throws IOException{
        Funding findFunding = new Funding();
        findFunding = fundingService.getFundingById(funding_id);
        log.info("funding is : {}",findFunding);

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


        ReturnFundingInfo returnFundingInfo = ReturnFundingInfo.builder()
                .funding_id(findFunding.getId())
                .funding_create_time(findFunding.getFunding_create_time())
                .funding_expired_time(findFunding.getFunding_expired_time())
                .funding_target_money(findFunding.getFunding_target_money())
                .funding_title(findFunding.getFunding_title())
                .member_id(findFunding.getMember().getId())
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
        return ResponseEntity.ok().body(returnFundingInfo);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BringFundingFromClient funding){
        log.info("fundingSave is {}",funding);

        Long fundingId = fundingService.fundingSave(funding);

        return ResponseEntity.ok()
                .body(fundingId);
    }


}





