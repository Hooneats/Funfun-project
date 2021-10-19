package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductSearchRepo extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.product_name like %:product_name% order by p.product_like_count desc")
    List<Product> productSearch(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>10000 order by p.product_like_count desc")
    List<Product> productSearch1(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>20000 order by p.product_like_count desc")
    List<Product> productSearch2(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>50000 order by p.product_like_count desc")
    List<Product> productSearch3(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>100000 order by p.product_like_count desc")
    List<Product> productSearch4(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% order by p.funding_count desc")
    List<Product> searchCount(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>10000 order by p.funding_count desc")
    List<Product> searchCount1(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>20000 order by p.funding_count desc")
    List<Product> searchCount2(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>50000 order by p.funding_count desc")
    List<Product> searchCount3(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>100000 order by p.funding_count desc")
    List<Product> searchCount4(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% order by p.product_price desc")
    List<Product> searchHigh(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>10000 order by p.product_price desc")
    List<Product> searchHigh1(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>20000 order by p.product_price desc")
    List<Product> searchHigh2(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>50000 order by p.product_price desc")
    List<Product> searchHigh3(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>100000 order by p.product_price desc")
    List<Product> searchHigh4(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% order by p.product_price asc")
    List<Product> searchLow(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>10000 order by p.product_price asc")
    List<Product> searchLow1(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>20000 order by p.product_price asc")
    List<Product> searchLow2(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>50000 order by p.product_price asc")
    List<Product> searchLow3(@Param("product_name") String product_name);

    @Query("select p from Product p where p.product_name like %:product_name% and p.product_price>100000 order by p.product_price asc")
    List<Product> searchLow4(@Param("product_name") String product_name);
}
