package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("select distinct o from Order o where o.member.id = :member_id and o.orderStatus='SUCCESS'")
    List<Order> findByMember_id(long member_id);

    Order findByMerchantUid(String merchant_uid);

}
