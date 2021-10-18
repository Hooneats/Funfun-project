package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;

import java.util.List;

public interface EventProductService {
    List<ProductListVo> getByEvent(long event_id);
}
