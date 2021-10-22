package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AdminRepo extends JpaRepository<Member, Long> {

    @Query("select m from Member m order by m.id asc")
    List<Member> findAllOrderById();


    @Query("select m from Member m where m.nic_name like %:nic_name%")
    List<Member> findByNicname(String nic_name);



}
