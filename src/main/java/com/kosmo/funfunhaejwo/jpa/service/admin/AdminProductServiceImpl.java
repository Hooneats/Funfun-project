package com.kosmo.funfunhaejwo.jpa.service.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminProductVo;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.repository.AdminProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {
    private final AdminProductRepo adminProductRepo;

    @Override
    public List<AdminProductVo> getProduct() {
        List<AdminProductVo> adminProductVoList = new ArrayList<>();
        List<Product> productList = adminProductRepo.findAll();
        for(Product product: productList){
            AdminProductVo adminProductVo = AdminProductVo
                    .builder()
                    .data1(product.getId())
                    .data2(product.getProduct_name())
                    .data3(product.getProduct_like_count())
                    .data4(product.getFunding_count())
                    .build();
            adminProductVoList.add(adminProductVo);
        }
        return adminProductVoList;
    }

    @Override
    public List<AdminProductVo> getSearch(String product_name) {
        List<AdminProductVo> adminProductVoList = new ArrayList<>();
        List<Product> productList = adminProductRepo.findBySearch(product_name);
        for(Product product: productList){
            AdminProductVo adminProductVo = AdminProductVo
                    .builder()
                    .data1(product.getId())
                    .data2(product.getProduct_name())
                    .data3(product.getProduct_like_count())
                    .data4(product.getFunding_count())
                    .build();
            adminProductVoList.add(adminProductVo);
        }
        return adminProductVoList;
    }
}
