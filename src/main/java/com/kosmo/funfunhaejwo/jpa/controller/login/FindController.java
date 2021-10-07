package com.kosmo.funfunhaejwo.jpa.controller.login;

import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.exception.EmailNullInputException;
import com.kosmo.funfunhaejwo.jpa.exception.ReturnExceptionResponse;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/find")
public class FindController {

    private final MemberService memberService;

    @PostMapping("/id")
    public ResponseEntity<?> findIdFromPhone(@RequestParam(required = false) String phone,
                                             HttpServletResponse response) throws IOException {
        List<String> findEmail = new ArrayList<>();
        if (phone == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
            } catch (EmailNullInputException en) {
                ReturnExceptionResponse.exceptionReturn(en, response, 401);
                return null;
            }
        }
        try {
            findEmail = memberService.findAllbyPhone(phone);
        } catch (UsernameNotFoundException ue) {
            ReturnExceptionResponse.exceptionReturn(ue, response, 402);
            return null;
        }
        return ResponseEntity.ok().body(findEmail);
    }

    @PostMapping("/id/from/email")
    public ResponseEntity<?> findIdFromEmail(@RequestParam(required = false) String email,
                                             HttpServletResponse response) throws IOException {
        Member findMember = new Member();
        if (email == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
            } catch (EmailNullInputException en) {
                ReturnExceptionResponse.exceptionReturn(en, response, 401);
                return null;
            }
        }
        try {
            findMember = memberService.getMemberByEmail(email);
        } catch (UsernameNotFoundException ue) {
            ReturnExceptionResponse.exceptionReturn(ue, response, 402);
            return null;
        }
        return ResponseEntity.ok().body(findMember.getEmail());
    }

    @PostMapping("/id/from/emailandphone")
    public ResponseEntity<?> findIdFromEmail(@RequestParam(required = false) String email,
                                             @RequestParam(required = false) String phone,
                                             HttpServletResponse response) throws IOException {
        Member findMember = new Member();
        List<String> allbyPhone = new ArrayList<>();
        if (email == null || phone == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
            } catch (EmailNullInputException en) {
                ReturnExceptionResponse.exceptionReturn(en, response, 401);
                return null;
            }
        }
        try {
            findMember = memberService.getMemberByEmail(email);
            allbyPhone = memberService.findAllbyPhone(phone);
        } catch (UsernameNotFoundException ue) {
            ReturnExceptionResponse.exceptionReturn(ue, response, 402);
            return null;
        }
        for (String id : allbyPhone) {
            if (id == findMember.getEmail()) {
                return ResponseEntity.ok().body(true);
            }
        }
        try {
            throw new Exception("id, 전화번호가 맞지 않습니다.");
        } catch (Exception e) {
            ReturnExceptionResponse.exceptionReturn(e, response, 402);
        }
        return null;
    }

    @PostMapping("/password/change")
    public ResponseEntity<?> changePassword(@RequestParam(required = false) String email,
                                            @RequestParam(required = false) String password,
                                            HttpServletResponse response) throws IOException {
        Member findMember = new Member();
        if (email == null || password == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
            } catch (EmailNullInputException en) {
                ReturnExceptionResponse.exceptionReturn(en, response, 401);
                return null;
            }
        }
        try {
            findMember = memberService.getMemberByEmail(email);
        } catch (UsernameNotFoundException ue) {
            ReturnExceptionResponse.exceptionReturn(ue, response, 402);
            return null;
        }
        Member modifiedMember = findMember.setPassword(password);
        memberService.saveMember(modifiedMember);
        return ResponseEntity.ok().body("변경되었습니다.");
    }
}
