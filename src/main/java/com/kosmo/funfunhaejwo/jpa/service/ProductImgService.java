package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;

import java.util.List;

public interface ProductImgService {
    List<ProductImg> getProductImgByProduct(Product product);

//    List<ProductImg> getPrductImgByCode(String img_code, Long product_id);
    List<ProductImg> getProductImgByImgCode(Product product, ImgCode img_code);
}
