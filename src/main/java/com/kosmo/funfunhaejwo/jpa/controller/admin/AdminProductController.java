package com.kosmo.funfunhaejwo.jpa.controller.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.AdminProductVo;
import com.kosmo.funfunhaejwo.jpa.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminProductController {
    private final AdminProductService adminProductService;

    @GetMapping("/product")
    public List<AdminProductVo> getProduct(){
        List<AdminProductVo> list = adminProductService.getProduct();
        return list;
    }
    @GetMapping("/product/{SearchName}")
    public List<AdminProductVo> getSearch(@PathVariable("SearchName") String product_name){
        List<AdminProductVo> list = adminProductService.getSearch(product_name);
        return list;
    }
}
