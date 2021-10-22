package com.kosmo.funfunhaejwo.jpa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kosmo.funfunhaejwo.jpa.domain.semi.FundingType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "FUNDING_SEQ_GENERATOR",
        sequenceName = "FUNDING_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class Funding {

    @Id
    @Column(name = "funding_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNDING_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String funding_title;
    private int funding_people_count;
    private Long funding_collected_money;
    private Long funding_target_money;

    @Enumerated(EnumType.STRING)
    private FundingType funding_type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime funding_create_time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime funding_expired_time;


}
