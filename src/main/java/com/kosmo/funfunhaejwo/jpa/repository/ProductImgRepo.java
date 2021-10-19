package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface ProductImgRepo extends JpaRepository<ProductImg, Long> {

    List<ProductImg> findByProduct(Product product);

    /*@Query("select pi.img_code,pi.file_info,pi.img_code,pi.product,pi.id from ProductImg pi where pi.product=:product and pi.img_code=:img_code")
    List<ProductImg> findByImgCode(String img_code, Long product_id);*/

    @Query("select pi.img_code,pi.file_info,pi.product,pi.id from ProductImg pi where pi.product=:product and pi.img_code=:img_code")
    List<ProductImg> findByImgCode(Product product, ImgCode img_code);


    @Query(value="select pi.file_info.file_src " +
            "from ProductImg pi " +
            "where pi.product.id=:product_id and pi.img_code='thumbnail'")
    String findThumbByProduct_id(@Param("product_id") long product_id);

}
