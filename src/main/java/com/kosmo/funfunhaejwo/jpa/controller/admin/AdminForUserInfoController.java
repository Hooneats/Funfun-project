package com.kosmo.funfunhaejwo.jpa.controller.admin;


import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.ReturnMemberByAdmin;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class AdminForUserInfoController {
    private final AdminService adminService;

    @GetMapping("/admin")
    public ResponseEntity<?> finList() {
        List<ReturnMemberByAdmin> returnResult = adminService.findListOrderId();
        log.info("#",returnResult);
        return ResponseEntity.ok().body(returnResult);
    }
    @GetMapping("/admin/search/{search}")
    public ResponseEntity<List<ReturnMemberByAdmin>> finnicname(@PathVariable("search") String nic_name){
        ResponseEntity<List<ReturnMemberByAdmin>> memberList = adminService.findByNic_name(nic_name);
        return memberList;

    }

}
