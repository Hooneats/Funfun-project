package com.kosmo.funfunhaejwo.jpa.controller.product;

import com.kosmo.funfunhaejwo.jpa.service.EventProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
