package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminProductRepo extends JpaRepository<Product, Long> {
    @Query("select p from Product p order by p.id")
    List<Product> findAll();

    @Query("select p from Product p where p.product_name like %:SearchName% order by p.id asc")
    List<Product> findBySearch(@Param("SearchName") String SearchName);
}
