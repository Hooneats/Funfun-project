package com.kosmo.funfunhaejwo.jpa.controller.product;

import com.kosmo.funfunhaejwo.jpa.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/WishListPage")
public class WishListController {
    private final WishListService wishListService;

    @GetMapping("/{member_id}")
    public List<ProductListVo> getWishList(@PathVariable("member_id") Long member_id){
        List<ProductListVo> list = wishListService.getWishList(member_id);
        return list;
    }
}
