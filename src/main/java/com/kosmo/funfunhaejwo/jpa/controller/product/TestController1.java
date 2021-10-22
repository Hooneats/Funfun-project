package com.kosmo.funfunhaejwo.jpa.controller.product;

import com.kosmo.funfunhaejwo.jpa.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/AdminProductList")
public class TestController1 {
    private final TestService testService;

    @PostMapping("/test")
    public List<TestVo> getTest(){
        List<TestVo> list = testService.getTest();
        return list;
    }
}
