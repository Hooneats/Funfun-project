package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.ProductPayment;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ProductPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductPaymentRepo extends JpaRepository<ProductPayment, Long> {

    Optional<ProductPayment> findByFunding(Funding funding);

    @Query("select distinct p from ProductPayment p where p.funding.id = :funding_id")
    Optional<ProductPayment> getProductPaymentByFunding(Long funding_id);

    @Query("select p from ProductPayment p order by p.id asc")
    List<ProductPayment> findAllOrderByProductPayment();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ProductPayment p set p.productPaymentStatus = :status where p.funding.id = :id")
    void update(@Param("id") Long funding_id, @Param("status") ProductPaymentStatus status);
}
