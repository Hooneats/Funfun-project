package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;

import java.util.List;

public interface ProductService {

     List<ProductListVo> getByCategory(long category_id);

     List<ProductListVo> getByCategory1(long category_id);

     List<ProductListVo> getByCategory2(long category_id);

     List<ProductListVo> getByCategory3(long category_id);

     List<ProductListVo> getByCategory4(long category_id);

     List<ProductListVo> getByCount(long category_id);

     List<ProductListVo> getByCount1(long category_id);

     List<ProductListVo> getByCount2(long category_id);

     List<ProductListVo> getByCount3(long category_id);

     List<ProductListVo> getByCount4(long category_id);

     List<ProductListVo> getByHigh(long category_id);

     List<ProductListVo> getByHigh1(long category_id);

     List<ProductListVo> getByHigh2(long category_id);

     List<ProductListVo> getByHigh3(long category_id);

     List<ProductListVo> getByHigh4(long category_id);

     List<ProductListVo> getByLow(long category_id);

     List<ProductListVo> getByLow1(long category_id);

     List<ProductListVo> getByLow2(long category_id);

     List<ProductListVo> getByLow3(long category_id);

     List<ProductListVo> getByLow4(long category_id);



     List<ProductListVo> getSearchList(String product_name);

}
