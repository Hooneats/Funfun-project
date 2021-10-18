package com.kosmo.funfunhaejwo.jpa.controller.product;

import com.kosmo.funfunhaejwo.jpa.service.OrderListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/OrderListPage")
public class OrderListController {
    private final OrderListService orderListService;

    @GetMapping("/{member_id}")
    public List<ProductListVo> getOrder(@PathVariable("member_id") Long member_id){
        List<ProductListVo> list = orderListService.getOrderList(member_id);
        return list;
    }
}
