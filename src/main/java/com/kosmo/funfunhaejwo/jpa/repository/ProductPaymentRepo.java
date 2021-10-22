package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.ProductPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

public interface ProductPaymentRepo extends JpaRepository<ProductPayment, Long> {

    Optional<ProductPayment> findByFunding(Funding funding);

    @Query("select distinct p from ProductPayment p where p.funding.id = :funding_id")
    Optional<ProductPayment> getProductPaymentByFunding(Long funding_id);

    @Query("select p from ProductPayment p order by p.id asc")
    List<ProductPayment> findAllOrderByProductPayment();
}
