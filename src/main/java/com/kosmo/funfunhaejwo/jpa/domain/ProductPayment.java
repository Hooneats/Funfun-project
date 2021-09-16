package com.kosmo.funfunhaejwo.jpa.domain;

import com.kosmo.funfunhaejwo.jpa.domain.semi.Address;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ProductPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@SequenceGenerator(
        name = "PRODUCT_PAYMENT_SEQ_GENERATOR",
        sequenceName = "PRODUCT_PAYMENT_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class ProductPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_PAYMENT_SEQ_GENERATOR")
    @Column(name = "product_payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductPaymentStatus productPaymentStatus;

    private int excess_cost;//초과금액

    private String w_product;//상품지급여부

    private String account;
    private String phone_number;

    @Embedded
    private Address address;



}
