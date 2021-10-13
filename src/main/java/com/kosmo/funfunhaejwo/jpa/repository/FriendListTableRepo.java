package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.FriendListTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendListTableRepo extends JpaRepository<FriendListTable, Long> {
    List<FriendListTable> findByMember_id(@Param(value="member_id") Long member_id);

    @Query(value="select * " +
            "from (select pi.file_src, m2.nic_name, m2.member_id " +
                  "from Friend_List f, Member m, Member m2, Profile_Img pi " +
                  "where f.member_id=m.member_id and f.friend_id=m2.member_id and m2.member_id=pi.member_id " +
                  "and f.member_id=:member_id and m2.nic_name like concat(concat('%',:searchName),'%')) " +
            "where rownum<=5"
            ,nativeQuery = true)
    List<Object[]> friendSearch(@Param("member_id") Long member_id, @Param("searchName") String searchName);
}
