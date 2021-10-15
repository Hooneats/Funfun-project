package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;
import com.kosmo.funfunhaejwo.jpa.domain.Event;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import com.kosmo.funfunhaejwo.jpa.repository.EventRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventProductServiceImpl implements EventProductService{
    private final ProductRepo productRepo;
    private final EventRepo eventRepo;

    @Override
    public List<ProductListVo> getByEvent(long event_id) {
        List<ProductListVo> productListVo = new ArrayList<>();
        Event event = eventRepo.findById(event_id).orElseThrow(() -> new IllegalArgumentException("이벤트 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByEvent(event);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }
}
