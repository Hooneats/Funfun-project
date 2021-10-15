package com.kosmo.funfunhaejwo.jpa.controller.login;

import com.kosmo.funfunhaejwo.jpa.controller.login.vo.ReturnLoginMemberInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.semi.LoginApi;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Role;
import com.kosmo.funfunhaejwo.jpa.exception.EmailNullInputException;
import com.kosmo.funfunhaejwo.jpa.exception.ReturnExceptionResponse;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/join")
public class JoinController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/email/check")
    public ResponseEntity<?> joinEmailCheck(@RequestParam(required = false) String email,
                                            HttpServletResponse response) throws IOException {
        if (email == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
            } catch (EmailNullInputException en) {
                ReturnExceptionResponse.exceptionReturn(en, response, 401);
                return null;
            }
        } else {
            try {
                Member memberByEmail = memberService.getMemberByEmail(email);
            } catch (UsernameNotFoundException ue) {
                Map<String, String> res = new Hashtable<>();
                res.put("res", "가입가능한 이메일 입니다.");
                return ResponseEntity.ok().body(res);
            }
        }
        try {
            throw new Exception("이미 존재하는 회원입니다.");
        } catch (Exception e) {
            ReturnExceptionResponse.exceptionReturn(e,response,405);
            return null;
        }

    }

    @PostMapping("/save/member")
    public ResponseEntity<ReturnLoginMemberInfo> joinFun(@RequestParam(required = false) String email,
                                                         @RequestParam(required = false) String nicname,
                                                         @RequestParam(required = false) String password,
                                                         @RequestParam(required = false) String phone,
                                                         HttpServletResponse response) throws IOException {
        if (email == null || nicname == null || password == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
            } catch (EmailNullInputException en) {
                ReturnExceptionResponse.exceptionReturn(en,response,401);
                return null;
            }
        }

        Member member = Member.builder()
                .email(email)
                .nic_name(nicname)
                .password(passwordEncoder.encode(password))
                .phone_number(phone)
                .login_api(LoginApi.EMAIL)
                .role(Role.USER)
                .build();

        Member savedMember = new Member();
        try {
            savedMember = memberService.saveMember(member);
        } catch (DataIntegrityViolationException de) {
            try {
                throw new Exception("이미 존재하는 회원입니다.");
            } catch (Exception e) {
                ReturnExceptionResponse.exceptionReturn(e,response,405);
                return null;
            }
        }

        ReturnLoginMemberInfo returnMember = ReturnLoginMemberInfo.builder()
                .id(savedMember.getId())
                .email(savedMember.getEmail())
                .nic_name(savedMember.getNic_name())
                .login_api(savedMember.getLogin_api().getKey())
                .role(savedMember.getRole().getKey())
                .profileImg(null)
                .build();

        return ResponseEntity.ok().body(returnMember);
    }

}