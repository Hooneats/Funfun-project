package com.kosmo.funfunhaejwo.jpa.domain;

import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@SequenceGenerator(
        name = "PRODUCT_IMG_SEQ_GENERATOR",
        sequenceName = "PRODUCT_IMG_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class ProductImg {

    @Id
    @Column(name = "product_img_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_IMG_SEQ_GENERATOR")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private File_info file_info;

    @Enumerated(EnumType.STRING)
    private ImgCode img_code;

}
