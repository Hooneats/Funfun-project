package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Like;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.exception.ProfileNotFoundException;
import com.kosmo.funfunhaejwo.jpa.repository.LikeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepo likeRepo;
    @Override
    public List<Like> getByMember(Member member){
        log.info("Will find LikeList, Member nic_name is {}", member.getNic_name());
        return likeRepo.findByMember(member);
    }
    @Override
    public List<Like> getByProduct(Product product){
        log.info("Will find LikeList, Product_name is {}",product.getProduct_name());
        return likeRepo.findByProduct(product);
    }
}
