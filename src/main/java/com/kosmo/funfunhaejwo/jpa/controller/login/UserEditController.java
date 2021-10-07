package com.kosmo.funfunhaejwo.jpa.controller.login;

import com.kosmo.funfunhaejwo.jpa.controller.login.vo.ReturnLoginMemberEditInfo;
import com.kosmo.funfunhaejwo.jpa.exception.EmailNullInputException;
import com.kosmo.funfunhaejwo.jpa.exception.ReturnExceptionResponse;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("bring/member")
public class UserEditController {

    private final MemberService memberService;

    @PostMapping("/edit/info")
    public ResponseEntity<ReturnLoginMemberEditInfo> editPageInfo(@RequestParam(required = false) Long memberId,
                                                                  HttpServletResponse response) throws IOException {
        if (memberId == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않은 요청입니다.");
            } catch (EmailNullInputException ee) {
                ReturnExceptionResponse.exceptionReturn(ee, response, 401);
            }
        }
        ReturnLoginMemberEditInfo returnLoginMemberEditInfo = memberService.editReturn(memberId);
        log.info("돌려보낼 값 : {}", returnLoginMemberEditInfo.toString());
        return ResponseEntity.ok().body(returnLoginMemberEditInfo);
    }

    @PostMapping("/edit/save")
    public ResponseEntity<?> editPageSave(@RequestBody ReturnLoginMemberEditInfo submitEditMember) {
        Boolean isSaved = memberService.editSave(submitEditMember);
        return ResponseEntity.ok().body(isSaved);
    }
}
