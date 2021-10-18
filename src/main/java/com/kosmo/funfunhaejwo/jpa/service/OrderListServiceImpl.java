package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.repository.OrderListRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderListServiceImpl implements OrderListService{
    private final OrderListRepo orderListRepo;

    @Override
    public List<ProductListVo> getOrderList(Long member_id) {
        List<ProductListVo> productListVo = new ArrayList<>();
        List<Product> productList = orderListRepo.findByOrder(member_id);
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
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.sub.name())) {
                    productVo.setSrc(FilePath.BASIC_FILE_PATH + productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }
}
