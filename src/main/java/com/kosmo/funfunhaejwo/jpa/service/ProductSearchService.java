package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;

import java.util.List;

public interface ProductSearchService {

    List<ProductListVo> getSearchList(String product_name);

    List<ProductListVo> getSearchList1(String product_name);

    List<ProductListVo> getSearchList2(String product_name);

    List<ProductListVo> getSearchList3(String product_name);

    List<ProductListVo> getSearchList4(String product_name);

    List<ProductListVo> getSearchCount(String product_name);

    List<ProductListVo> getSearchCount1(String product_name);

    List<ProductListVo> getSearchCount2(String product_name);

    List<ProductListVo> getSearchCount3(String product_name);

    List<ProductListVo> getSearchCount4(String product_name);

    List<ProductListVo> getSearchHigh(String product_name);

    List<ProductListVo> getSearchHigh1(String product_name);

    List<ProductListVo> getSearchHigh2(String product_name);

    List<ProductListVo> getSearchHigh3(String product_name);

    List<ProductListVo> getSearchHigh4(String product_name);

    List<ProductListVo> getSearchLow(String product_name);

    List<ProductListVo> getSearchLow1(String product_name);

    List<ProductListVo> getSearchLow2(String product_name);

    List<ProductListVo> getSearchLow3(String product_name);

    List<ProductListVo> getSearchLow4(String product_name);
}
