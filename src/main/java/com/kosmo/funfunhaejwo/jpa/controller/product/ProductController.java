package com.kosmo.funfunhaejwo.jpa.controller.product;

import com.kosmo.funfunhaejwo.jpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ProductListPage")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/{category}")
    public List<ProductListVo> getList(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCategory(category_id);
        return list;
    }
    @PostMapping("/1/{category}")
    public List<ProductListVo> getList1(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCategory1(category_id);
        return list;
    }
    @PostMapping("/2/{category}")
    public List<ProductListVo> getList2(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCategory2(category_id);
        return list;
    }
    @PostMapping("/3/{category}")
    public List<ProductListVo> getList3(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCategory3(category_id);
        return list;
    }
    @PostMapping("/4/{category}")
    public List<ProductListVo> getList4(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCategory4(category_id);
        return list;
    }
    @PostMapping("/A/{category}")
    public List<ProductListVo> getCountList(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCount(category_id);
        return list;
    }
    @PostMapping("/A/1/{category}")
    public List<ProductListVo> getCountList1(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCount1(category_id);
        return list;
    }
    @PostMapping("/A/2/{category}")
    public List<ProductListVo> getCountList2(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCount2(category_id);
        return list;
    }
    @PostMapping("/A/3/{category}")
    public List<ProductListVo> getCountList3(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCount3(category_id);
        return list;
    }
    @PostMapping("/A/4/{category}")
    public List<ProductListVo> getCountList4(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByCount4(category_id);
        return list;
    }
    @PostMapping("/B/{category}")
    public List<ProductListVo> getHighList(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByHigh(category_id);
        return list;
    }
    @PostMapping("/B/1/{category}")
    public List<ProductListVo> getHighList1(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByHigh1(category_id);
        return list;
    }
    @PostMapping("/B/2/{category}")
    public List<ProductListVo> getHighList2(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByHigh2(category_id);
        return list;
    }
    @PostMapping("/B/3/{category}")
    public List<ProductListVo> getHighList3(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByHigh3(category_id);
        return list;
    }
    @PostMapping("/B/4/{category}")
    public List<ProductListVo> getHighList4(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByHigh4(category_id);
        return list;
    }
    @PostMapping("/C/{category}")
    public List<ProductListVo> getLowList(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByLow(category_id);
        return list;
    }
    @PostMapping("/C/1/{category}")
    public List<ProductListVo> getLowList1(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByLow1(category_id);
        return list;
    }
    @PostMapping("/C/2/{category}")
    public List<ProductListVo> getLowList2(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByLow2(category_id);
        return list;
    }
    @PostMapping("/C/3/{category}")
    public List<ProductListVo> getLowList3(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByLow3(category_id);
        return list;
    }
    @PostMapping("/C/4/{category}")
    public List<ProductListVo> getLowList4(@PathVariable("category") long category_id){
        List<ProductListVo>  list = productService.getByLow4(category_id);
        return list;
    }
    @GetMapping("/search/{product_name}")
    public List<ProductListVo> getSearch(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productService.getSearchList(product_name);
        return list;
    }
}