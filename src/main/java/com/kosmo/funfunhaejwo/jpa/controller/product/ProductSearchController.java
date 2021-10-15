package com.kosmo.funfunhaejwo.jpa.controller.product;

import com.kosmo.funfunhaejwo.jpa.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ProductListPage")
public class ProductSearchController {
    private final ProductSearchService productSearchService;

    @GetMapping("/search/{product_name}")
    public List<ProductListVo> getSearch(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchList(product_name);
        return list;
    }
    @GetMapping("/search/1/{product_name}")
    public List<ProductListVo> getSearch1(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchList1(product_name);
        return list;
    }
    @GetMapping("/search/2/{product_name}")
    public List<ProductListVo> getSearch2(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchList2(product_name);
        return list;
    }
    @GetMapping("/search/3/{product_name}")
    public List<ProductListVo> getSearch3(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchList3(product_name);
        return list;
    }
    @GetMapping("/search/4/{product_name}")
    public List<ProductListVo> getSearch4(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchList4(product_name);
        return list;
    }

    @GetMapping("/search/A/{product_name}")
    public List<ProductListVo> getSearchCount(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchCount(product_name);
        return list;
    }
    @GetMapping("/search/A/1/{product_name}")
    public List<ProductListVo> getSearchCount1(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchCount1(product_name);
        return list;
    }
    @GetMapping("/search/A/2/{product_name}")
    public List<ProductListVo> getSearchCount2(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchCount2(product_name);
        return list;
    }
    @GetMapping("/search/A/3/{product_name}")
    public List<ProductListVo> getSearchCount3(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchCount3(product_name);
        return list;
    }
    @GetMapping("/search/A/4/{product_name}")
    public List<ProductListVo> getSearchCount4(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchCount4(product_name);
        return list;
    }

    @GetMapping("/search/B/{product_name}")
    public List<ProductListVo> getSearchHigh(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchHigh(product_name);
        return list;
    }
    @GetMapping("/search/B/1/{product_name}")
    public List<ProductListVo> getSearchHigh1(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchHigh1(product_name);
        return list;
    }
    @GetMapping("/search/B/2/{product_name}")
    public List<ProductListVo> getSearchHigh2(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchHigh2(product_name);
        return list;
    }
    @GetMapping("/search/B/3/{product_name}")
    public List<ProductListVo> getSearchHigh3(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchHigh3(product_name);
        return list;
    }
    @GetMapping("/search/B/4/{product_name}")
    public List<ProductListVo> getSearchHigh4(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchHigh4(product_name);
        return list;
    }

    @GetMapping("/search/C/{product_name}")
    public List<ProductListVo> getSearchLow(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchLow(product_name);
        return list;
    }
    @GetMapping("/search/C/1/{product_name}")
    public List<ProductListVo> getSearchLow1(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchLow1(product_name);
        return list;
    }
    @GetMapping("/search/C/2/{product_name}")
    public List<ProductListVo> getSearchLow2(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchLow2(product_name);
        return list;
    }
    @GetMapping("/search/C/3/{product_name}")
    public List<ProductListVo> getSearchLow3(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchLow3(product_name);
        return list;
    }
    @GetMapping("/search/C/4/{product_name}")
    public List<ProductListVo> getSearchLow4(@PathVariable("product_name") String product_name){
        List<ProductListVo> list = productSearchService.getSearchLow4(product_name);
        return list;
    }
}
