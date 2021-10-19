package com.kosmo.funfunhaejwo.jpa.controller.login.vo;

import com.kosmo.funfunhaejwo.jpa.domain.Like;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Address;
import com.kosmo.funfunhaejwo.jpa.domain.semi.LoginApi;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Role;
import com.kosmo.funfunhaejwo.jpa.exception.BadRequestLoginApiException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnLoginMemberEditInfo {

    private Long id;

    private String email;

    private String nic_name;

    private String  phone_number;

    private String city;

    private String street;

    private String zipcode;

}
