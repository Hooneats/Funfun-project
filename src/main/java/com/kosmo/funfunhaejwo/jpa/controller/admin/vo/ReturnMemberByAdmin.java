package com.kosmo.funfunhaejwo.jpa.controller.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnMemberByAdmin {
    private Long data1;
    private String data2;
    private String data3;
    private String data4;
    private Integer data5;
}
