package com.kosmo.funfunhaejwo.jpa.service.admin;


import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.ReturnMemberByAdmin;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    List<ReturnMemberByAdmin> findListOrderId();

    ResponseEntity<List<ReturnMemberByAdmin>> findByNic_name(String nic_name);

}
