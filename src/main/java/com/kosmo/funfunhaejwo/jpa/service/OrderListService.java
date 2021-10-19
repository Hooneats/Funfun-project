package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;

import java.util.List;

public interface OrderListService {
    List<ProductListVo> getOrderList(Long member_id);
}
