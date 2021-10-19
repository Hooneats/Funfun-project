package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishListRepo extends JpaRepository<Product, Long> {

    @Query("select p from Product p, Like l where l.member.id=:member_id and l.product.id=p.id")
    List<Product> findByWish(@Param("member_id") Long member_id);
}
