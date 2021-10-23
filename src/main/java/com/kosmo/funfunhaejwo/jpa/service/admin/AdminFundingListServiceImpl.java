package com.kosmo.funfunhaejwo.jpa.service.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminExpiredFundingVo;
import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminFundingListVo;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductPaymentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminFundingListServiceImpl implements AdminFundingListService {
    private final FundingRepo fundingRepo;
    private final ProductPaymentRepo productPaymentRepo;

    @Override
    public List<AdminFundingListVo> getFundingList() {
        List<AdminFundingListVo> adminFundingListVoList = new ArrayList<>();
        List<Object[]> fundingList = fundingRepo.findFunding();
        for(Object[] item: fundingList){
            adminFundingListVoList.add(new AdminFundingListVo(Long.parseLong(String.valueOf(item[0])),(String)item[1],(String)item[2],Integer.parseInt(String.valueOf(item[3])), LocalDateTime.parse(String.valueOf(item[4])),LocalDateTime.parse(String.valueOf(item[5])), FilePath.BASIC_FILE_PATH+(String) item[6]));
        }
        return adminFundingListVoList;
    }

    @Override
    public List<AdminExpiredFundingVo> getExpired() {
        List<AdminExpiredFundingVo> adminExpiredFundingVoList = new ArrayList<>();
        List<Object[]> expiredList = fundingRepo.findExpired();
        for(Object[] item: expiredList){
            adminExpiredFundingVoList.add(new AdminExpiredFundingVo(Long.parseLong(String.valueOf(item[0])),(String) item[1], (String) item[2], Long.parseLong(String.valueOf(item[3]))
                    ,Long.parseLong(String.valueOf(item[4])),LocalDateTime.parse(String.valueOf(item[5])),
                    String.valueOf(item[6]), FilePath.BASIC_FILE_PATH+item[7]));
        }
        return adminExpiredFundingVoList;
    }
}
