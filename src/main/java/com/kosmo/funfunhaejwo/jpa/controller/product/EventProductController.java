package com.kosmo.funfunhaejwo.jpa.controller.product;

import com.kosmo.funfunhaejwo.jpa.service.EventProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/EventProduct")
public class EventProductController {
    private final EventProductService eventProductService;

    @GetMapping("/{event}")
    public List<ProductListVo> getList(@PathVariable("event") long event_id){
        List<ProductListVo>  list = eventProductService.getByEvent(event_id);
        return list;
    }
}

