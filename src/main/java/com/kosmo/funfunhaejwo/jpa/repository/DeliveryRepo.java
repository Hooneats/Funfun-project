package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Delivery;
import com.kosmo.funfunhaejwo.jpa.domain.ProductPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<Delivery, Long> {
    Delivery findByProductPayment(ProductPayment productPayment);
}
