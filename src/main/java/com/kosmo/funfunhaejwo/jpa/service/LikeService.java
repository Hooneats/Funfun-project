package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Like;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.Product;

import java.util.List;

public interface LikeService {
    List<Like> getByMember(Member member);

    List<Like> getByProduct(Product product);

//    Long getLikeCount(Long product_id);
}
