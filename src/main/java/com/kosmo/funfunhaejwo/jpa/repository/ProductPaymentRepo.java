package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.ProductPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductPaymentRepo extends JpaRepository<ProductPayment, Long> {

    Optional<ProductPayment> findByFunding(Funding funding);
}
