package com.kosmo.funfunhaejwo.jpa.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "FRIEND_LIST_SEQ_GENERATOR",
        sequenceName = "FRIEND_LIST_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
@Table(name = "FRIEND_LIST")
public class FriendListTable {

    @Id
    @Column(name = "friend_list_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRIEND_LIST_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //이건 관계맺기가.... 연관관계 맺기 안하고 그냥 컬럼 만들었기에 주의하자
    @Column(name = "friend_id")
    private Long friend_id;


}
