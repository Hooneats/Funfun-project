package com.kosmo.funfunhaejwo.jpa.domain.semi;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;



@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {

    //생성시에 데이트 추가됨
    @CreatedDate
    private LocalDateTime created;

    //생성 및 업데이트시에 데이트 추가됨
    @LastModifiedDate
    private LocalDateTime updated;

}
