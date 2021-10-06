package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductImgRepo extends JpaRepository<ProductImg, Long> {
    @Query(value="select pi.file_info.file_src " +
            "from ProductImg pi " +
            "where pi.product.id=:product_id and pi.img_code='thumbnail'")
    String findThumbByProduct_id(@Param("product_id") long product_id);
}
