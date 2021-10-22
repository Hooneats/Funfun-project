package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.semi.FundingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

public interface FundingRepo extends JpaRepository<Funding, Long> {
    List<Funding> findByMember_id(@Param(value="member_id") Long member_id);


//    Page<Funding> findAllByFunding_expired_timeBetween(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
    @Query(value="select * " +
                 "from (select f.funding_id, f.product_id, f.funding_title, f.funding_expired_time, " +
                              "f.funding_collected_money, f.funding_target_money, m.nic_name " +
                       "from member m, funding f " +
                       "where f.member_id=m.member_id and " +
                       "f.funding_expired_time between :fromDate and :toDate) " +
                 "where rownum<=10"
    ,nativeQuery = true)

    List<Object[]> findDeadline(LocalDateTime fromDate, LocalDateTime toDate);


    Optional<Funding> findFundingById(Long funding_id);

    List<Funding> findFundingByMember(Long member_id);

//    List<Funding> findFundingByFunding_type(@Param(value="funding_type")String funding_type);





}
