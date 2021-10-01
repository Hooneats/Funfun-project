package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.FriendListTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendListTableRepo extends JpaRepository<FriendListTable, Long> {
    List<FriendListTable> findByMember_id(@Param(value="member_id") Long member_id);

    @Query(value="select f.id, m, m2 " +
            "from FriendListTable f, Member m, Member m2 " +
            "where f.member.id=m.id and f.friend.id=m2.id " +
            "and f.member.id=:member_id and m2.nic_name like concat(concat('%',:searchName),'%')"
            ,nativeQuery = false)
    List<FriendListTable> friendSearch(@Param("member_id") Long member_id, @Param("searchName") String searchName);
}
