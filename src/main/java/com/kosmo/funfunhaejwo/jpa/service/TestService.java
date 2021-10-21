package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;
import com.kosmo.funfunhaejwo.jpa.controller.product.TestVo;
import com.kosmo.funfunhaejwo.jpa.domain.Product;

import java.util.List;

public interface TestService {
    List<TestVo> getTest();
}
