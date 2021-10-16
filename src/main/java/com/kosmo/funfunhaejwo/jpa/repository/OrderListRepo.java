package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderListRepo extends JpaRepository<Product, Long> {
    @Query("select p from Product p, Order o where o.member.id=:member_id and p.id = o.product.id")
    List<Product> findByOrder(@Param("member_id") Long member_id);
}
