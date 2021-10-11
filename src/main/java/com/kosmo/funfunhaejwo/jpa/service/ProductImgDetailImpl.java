package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import com.kosmo.funfunhaejwo.jpa.repository.ProductImgRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductImgDetailImpl implements ProductImgService{
    private final ProductImgRepo productImgRepo;

    @Override
    public List<ProductImg> getProductImgByProduct(Product product){
        log.info("Find ProductImg by Id {}",product.getId());
        return productImgRepo.findByProduct(product);
    }

   /* @Override
    public List<ProductImg> getPrductImgByCode(String img_code, Long product_id){
        log.info("Find ProductImg by Id {} and code{}",product_id,img_code);
        return productImgRepo.findByImgCode(img_code, product_id);
    }*/

    @Override
    public List<ProductImg> getProductImgByImgCode(Product product, ImgCode img_code){
        log.info("Find ProductImg by Id {} and code{}",product.getId(),img_code);
        return productImgRepo.findByImgCode(product,img_code);
    }
}
