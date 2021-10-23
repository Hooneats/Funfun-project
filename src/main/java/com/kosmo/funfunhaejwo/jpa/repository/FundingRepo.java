package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

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

    @Query("select f.id, f.funding_title, m.email, f.funding_people_count, f.funding_create_time, f.funding_expired_time" +
            ",pi.file_info.file_src from Funding f, Member m, ProductImg pi where m.id=f.member.id and f.product.id = pi.product.id " +
            "and pi.img_code='thumbnail' order by f.id asc")
    List<Object[]> findFunding();

    @Query("select f.id, f.funding_title, m.email, f.funding_people_count, f.funding_create_time, f.funding_expired_time" +
            ",pi.file_info.file_src from Funding f, Member m, ProductImg pi where m.id=f.member.id and f.product.id = pi.product.id " +
            "and pi.img_code='thumbnail' and f.funding_title like %:funding_name% order by f.id asc")
    List<Object[]> searchFunding(@Param("funding_name") String funding_name);

    @Query("select f from Funding f order by f.id asc")
    List<Funding> findAllOrderByFundingId();
//    List<Funding> findFundingByFunding_type(@Param(value="funding_type")String funding_type);





}
