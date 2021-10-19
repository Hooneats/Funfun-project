package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Like;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.repository.LikeRepo;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService{

    private final ProductRepo productRepo;
    private final MemberRepo memberRepo;
    private final LikeRepo likeRepo;


    @Override
    public Product getProductById(Long product_id){
        log.info("Find Product by Id {}",product_id);
        return productRepo.findById(product_id).orElseThrow(() -> new UsernameNotFoundException("상품을 찾을 수 없습니다."));

    }


    @Override
    public void likeCountUp(Long product_id, Long member_id, Boolean select){
        Member member = memberRepo.findById(member_id).orElseThrow(() -> new IllegalArgumentException("가입되어있는 회원이 아닙니다."));
        Product product = productRepo.findById(product_id).orElseThrow(() -> new IllegalArgumentException("찾으시는 상품이 없습니다."));
        product.likeControl(select);
        Like findLike = likeRepo.findByMemberAndProduct(member, product);
        if (findLike != null) {
            likeRepo.deleteByMemberAndProduct(member,product);
            return;
        }
        Like likeBuild = Like.builder().product(product)
                .member(member)
                .build();
        if(select){
            likeRepo.save(likeBuild);
        }
    }


}
