package com.kosmo.funfunhaejwo.jpa.service.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminFundingListVo;

import java.util.List;

public interface AdminFundingListService {
    List<AdminFundingListVo> getFundingList();
}
