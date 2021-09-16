package com.kosmo.funfunhaejwo.jpa.controller.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EXMemberInfoDao {

    private Long id;
    private String name;
    private String email;


}
