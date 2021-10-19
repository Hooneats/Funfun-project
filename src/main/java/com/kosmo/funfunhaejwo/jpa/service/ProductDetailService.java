package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Product;


public interface ProductDetailService {

    Product getProductById(Long product_id);

//    Long getLikeById(Long product_id);
//    Long likeCountUp(Long product_id);
    void likeCountUp(Long product_id, Long member_id, Boolean select);
}
