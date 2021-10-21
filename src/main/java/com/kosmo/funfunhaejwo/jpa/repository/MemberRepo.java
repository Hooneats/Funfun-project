package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);

    @Query("select m from Member m where m.phone_number=:phone")
    List<Member> findByPhone_number(String phone);


}
