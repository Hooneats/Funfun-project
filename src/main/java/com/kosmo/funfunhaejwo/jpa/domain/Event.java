package com.kosmo.funfunhaejwo.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@SequenceGenerator(
        name = "EVENT_SEQ_GENERATOR",
        sequenceName = "EVENT_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_SEQ_GENERATOR")
    private Long id;

    private String event_title;

    @OneToMany(mappedBy = "event")
    private List<Product> products = new ArrayList<>();
}