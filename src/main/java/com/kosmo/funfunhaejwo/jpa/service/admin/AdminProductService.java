package com.kosmo.funfunhaejwo.jpa.service.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminProductVo;

import java.util.List;

public interface AdminProductService {
    List<AdminProductVo> getProduct();

    List<AdminProductVo> getSearch(String product_name);
}
