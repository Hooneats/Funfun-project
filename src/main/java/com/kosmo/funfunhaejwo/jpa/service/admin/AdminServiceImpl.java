package com.kosmo.funfunhaejwo.jpa.service.admin;

import com.kosmo.funfunhaejwo.jpa.controller.admin.vo.ReturnMemberByAdmin;
import com.kosmo.funfunhaejwo.jpa.domain.Member;

import com.kosmo.funfunhaejwo.jpa.repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService{
    private final AdminRepo adminrepo;

    
    @Override
    public List<ReturnMemberByAdmin> findListOrderId() {
        List<Member> allMember = adminrepo.findAllOrderById();
        List<ReturnMemberByAdmin> returnMemberByAdmins = new ArrayList<>();
        allMember.forEach(res->{

            ReturnMemberByAdmin returnMemberByAdmin = new ReturnMemberByAdmin(res.getId(), res.getNic_name(), res.getEmail(), res.getRole().getKey(), res.getFundingList().size());

            returnMemberByAdmins.add(returnMemberByAdmin);
        });
        return returnMemberByAdmins;
    }
    @Override
    public ResponseEntity<List<ReturnMemberByAdmin>> findByNic_name(String nic_name){
        System.out.println("#### nic_name = " + nic_name);
        List<ReturnMemberByAdmin> returnMemberByAdminList = new ArrayList<>();
        List<Member> byNicname = adminrepo.findByNicname(nic_name);
        for (Member member : byNicname) {
            ReturnMemberByAdmin buildAdminNeedsUsers = ReturnMemberByAdmin.builder()
                    .data1(member.getId())
                    .data2(member.getNic_name())
                    .data3(member.getEmail())
                    .data4(member.getRole().getKey())
                    .data5(member.getFundingList().size())
                    .build();
            returnMemberByAdminList.add(buildAdminNeedsUsers);
        }
        return ResponseEntity.ok().body(returnMemberByAdminList);
    }
}
