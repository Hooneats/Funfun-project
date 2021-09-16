package com.kosmo.funfunhaejwo.jpa.domain;

import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "EVENT_IMG_SEQ_GENERATOR",
        sequenceName = "EVENT_IMG_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
@Table(name = "event_img")
public class EventImg {

    @Id
    @Column(name = "event_img_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_IMG_SEQ_GENERATOR")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Embedded
    private File_info file_info;

    @Enumerated(EnumType.STRING)
    private ImgCode img_code;

}
