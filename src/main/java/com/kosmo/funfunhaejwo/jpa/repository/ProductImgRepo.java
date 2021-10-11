package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductImgRepo extends JpaRepository<ProductImg, Long> {

    List<ProductImg> findByProduct(Product product);

    /*@Query("select pi.img_code,pi.file_info,pi.img_code,pi.product,pi.id from ProductImg pi where pi.product=:product and pi.img_code=:img_code")
    List<ProductImg> findByImgCode(String img_code, Long product_id);*/

    @Query("select pi.img_code,pi.file_info,pi.product,pi.id from ProductImg pi where pi.product=:product and pi.img_code=:img_code")
    List<ProductImg> findByImgCode(Product product, ImgCode img_code);


}
