package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.funding.vo.BringFundingFromClient;
import com.kosmo.funfunhaejwo.jpa.domain.Funding;

public interface FundingService {
    Funding createFunding(Funding funding);

    Funding getFundingById(Long funding_id);

    Long fundingSave(BringFundingFromClient funding);
}
