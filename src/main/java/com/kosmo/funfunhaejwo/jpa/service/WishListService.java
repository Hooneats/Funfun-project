package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;

import java.util.List;

public interface WishListService {
    List<ProductListVo> getWishList(Long member_id);
}
