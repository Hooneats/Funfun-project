package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;
import com.kosmo.funfunhaejwo.jpa.controller.product.TestVo;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.repository.TestRepo;
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
public class TestServiceImpl implements TestService{
    private final TestRepo testRepo;

    @Override
    public List<TestVo> getTest() {
        List<TestVo> testVoList = new ArrayList<>();
        List<Product> productList = testRepo.findAll();
        for(Product product: productList){
            TestVo testVo = TestVo
                    .builder()
                    .data1(product.getId())
                    .data2(product.getProduct_name())
                    .data3(product.getProduct_like_count())
                    .data4(product.getFunding_count())
                    .data8("delete")
                    .build();
            testVoList.add(testVo);
        }
        return testVoList;
    }
}
