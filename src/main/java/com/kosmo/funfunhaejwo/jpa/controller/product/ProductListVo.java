package com.kosmo.funfunhaejwo.jpa.controller.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListVo {
    private long productId;
    private String title;
    private long price;
    private int likeRate;
    private int fundingCount;
    private String brand;
    private String src;
    private long categoryId;
}
