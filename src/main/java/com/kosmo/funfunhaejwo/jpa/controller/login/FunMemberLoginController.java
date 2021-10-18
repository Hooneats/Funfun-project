package com.kosmo.funfunhaejwo.jpa.controller.login;

import com.kosmo.funfunhaejwo.jpa.controller.login.vo.ReturnLoginMemberInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import com.kosmo.funfunhaejwo.jpa.exception.EmailNullInputException;
import com.kosmo.funfunhaejwo.jpa.exception.ProfileNotFoundException;
import com.kosmo.funfunhaejwo.jpa.exception.ReturnExceptionResponse;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import com.kosmo.funfunhaejwo.jpa.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fun")
public class FunMemberLoginController {
    private final MemberService memberService;
    private final ProfileService profileService;

    @PostMapping("/get/memberInfo")
    public ResponseEntity<ReturnLoginMemberInfo> getMemberInfoFromFun(@RequestParam(required = false) String email,
                                                                      HttpServletRequest request,
                                                                      HttpServletResponse response) throws IOException {

        Member findMember = new Member();
        ProfileImg findProfile = new ProfileImg();
        if (email == null || email.equals("")) {
            try {
                throw new EmailNullInputException("양식에 맞지않은 요청입니다.");
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
        try {
            findProfile = profileService.getProfileImgByMember(findMember);
        } catch (ProfileNotFoundException pn) {
            /**
             * 현재는 그냥 없는 경우도 처리하지만 실재로는 사진이 없는 유저는 기본이미지를 주어서 따로 안잡아줘도 되게끔하자
             * */
            ReturnLoginMemberInfo returnMemberInfo = ReturnLoginMemberInfo.builder().id(findMember.getId())
                    .email(findMember.getEmail())
                    .nic_name(findMember.getNic_name())
                    .login_api(findMember.getLogin_api().getKey())
                    .role(findMember.getRole().getKey())
                    .build();
            return ResponseEntity.ok().body(returnMemberInfo);
        }

        ReturnLoginMemberInfo returnMemberInfo = ReturnLoginMemberInfo.builder().id(findMember.getId())
                .email(findMember.getEmail())
                .nic_name(findMember.getNic_name())
                .login_api(findMember.getLogin_api().getKey())
                .role(findMember.getRole().getKey())
                .profileImg(FilePath.BASIC_FILE_PATH+findProfile.getFile_info().getFile_src())
                .build();

        log.info(returnMemberInfo.toString());
        return ResponseEntity.ok().body(returnMemberInfo);

    }

}
