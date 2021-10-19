package com.kosmo.funfunhaejwo.jpa.controller.login.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnLoginMemberInfo {
    private Long id;
    private String email;
    private String nic_name;
    private String login_api;
    private String role;
    private String  profileImg;
}
