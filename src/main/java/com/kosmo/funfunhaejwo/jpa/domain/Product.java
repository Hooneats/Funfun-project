package com.kosmo.funfunhaejwo.jpa.domain;

import com.kosmo.funfunhaejwo.jpa.domain.semi.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@SequenceGenerator(
        name = "PRODUCT_SEQ_GENERATOR",
        sequenceName = "PRODUCT_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ_GENERATOR")
    @Column(name = "product_id")
    private Long id;
    private String product_name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private int product_stock;

    private Long product_price;

    @OneToMany(mappedBy = "product")
    private List<ProductImg> productImgs = new ArrayList<>();

    //    펀딩수
    private int funding_count;
    // 좋아요수
    private int product_like_count;

    private String product_brand;
//    @OneToMany
//    private List<ProductImg> product_img_list = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_ID")
    private Event event;

    @OneToMany(mappedBy = "product")
    private List<Like> LikeList = new ArrayList<>();

    public Product likeControl(Boolean select) {
        if (select) {
            this.product_like_count++;
        } else {
            this.product_like_count--;
        }
        return this;
    }

}