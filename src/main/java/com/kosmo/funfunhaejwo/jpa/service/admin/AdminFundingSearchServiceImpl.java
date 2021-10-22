package com.kosmo.funfunhaejwo.jpa.service.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminFundingListVo;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
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
public class AdminFundingSearchServiceImpl implements AdminFundingSearchService{
    private final FundingRepo fundingRepo;

    @Override
    public List<AdminFundingListVo> getFundingList(String funding_name) {
        List<AdminFundingListVo> adminFundingListVoList = new ArrayList<>();
        List<Object[]> fundingList = fundingRepo.searchFunding(funding_name);
        for(Object[] item: fundingList){
            adminFundingListVoList.add(new AdminFundingListVo(Long.parseLong(String.valueOf(item[0])),(String)item[1],(String)item[2],Integer.parseInt(String.valueOf(item[3])), LocalDateTime.parse(String.valueOf(item[4])),LocalDateTime.parse(String.valueOf(item[5])), FilePath.BASIC_FILE_PATH+(String) item[6],"img"));
        }
        return adminFundingListVoList;
    }
}
