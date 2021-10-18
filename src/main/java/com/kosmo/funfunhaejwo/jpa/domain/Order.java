package com.kosmo.funfunhaejwo.jpa.domain;


import com.kosmo.funfunhaejwo.jpa.domain.semi.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
@SequenceGenerator(
        name = "ORDERS_SEQ_GENERATOR",
        sequenceName = "ORDERS_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class Order {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ_GENERATOR")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    private Long total_payment;

    @Column(name = "merchant_uid")
    private String merchantUid;

    @Column(name = "i_port_id", length = 50)
    private String  i_port_id;
    @Column(name = "pg_id", length = 50)
    private String pg_id;

    @Column(name = "order_date")
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus; //주문상태 [ORDER, CANCEL]

}
