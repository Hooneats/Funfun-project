package com.kosmo.funfunhaejwo.jpa.controller.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.funfunhaejwo.jpa.controller.login.vo.ReturnLoginMemberInfo;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.domain.semi.LoginApi;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Role;
import com.kosmo.funfunhaejwo.jpa.exception.*;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import com.kosmo.funfunhaejwo.jpa.service.ProfileService;
import com.kosmo.funfunhaejwo.security.config.vo.JWTGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class OauthLoginController {

    private final MemberService memberService;
    private final ProfileService profileService;

    @PostMapping("/oauth/get/tokens") // oauth ????????? ?????? DB ?????? ???????????? ????????? ????????????
    public void apiLogin(@RequestParam(required = false) String email,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        try {
            if (email == null) {
                throw new EmailNullInputException("????????? ????????? ????????????");
            } else {
                Member member = memberService.getMemberByEmail(email);
                if (member != null) {

                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(member.getRole().getKey()));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(member.getEmail(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    String access_jwt = JWTGenerator.create_access_JWT(member.getEmail(), request.getRequestURL().toString(), authorities, 10);
                    String refresh_jwt = JWTGenerator.create_refresh_JWT(member.getEmail(), request.getRequestURL().toString(), 30);

                    Map<String, String> tokens = new HashMap<>();
                    tokens.put("access_token", access_jwt);
                    tokens.put("refresh_token", refresh_jwt);

//                    HttpServletResponse httpResponse = CrossHeader.corsHeader(response);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                }
            }
        } catch (EmailNullInputException en) {
            ReturnExceptionResponse.exceptionReturn(en, response, 401);
        } catch (UsernameNotFoundException ue) {
            ReturnExceptionResponse.exceptionReturn(ue, response, 402);
        } catch (Exception e) {
            ReturnExceptionResponse.exceptionReturn(e, response, 403);
        }

    }

    @PostMapping("/oauth/get/tokens/refresh_token") // ???????????? ???????????? ????????? ????????? ?????? ??????
    public void getAccessTokenFromRefreshToken(@RequestParam(required = false) String email,
                                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (email == null) {
                try {
                    throw new EmailNullInputException("????????? ?????? ?????? ???????????????.");
                } catch (EmailNullInputException ee) {
                    ReturnExceptionResponse.exceptionReturn(ee, response, 401);
                }
            }
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            log.info("# authorizationHeader is {}", authorizationHeader);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                log.info("# ???????????? ??? email {}", email);
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("KosmoFunFunHaeJow".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                log.info("# ???????????? ??? username {}", username);

                Member memberByEmail = memberService.getMemberByEmail(username);
                if (!memberByEmail.getEmail().equals(email)) {
                    throw new NotEqualsMemberException("????????? ?????? ????????? ???????????????");
                }

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(memberByEmail.getRole().getKey()));
                String access_jwt = JWTGenerator.create_access_JWT(username, request.getRequestURL().toString(), authorities, 10);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_jwt);
                tokens.put("refresh_token", refresh_token);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }

            if (authorizationHeader == null || authorizationHeader.equals("")) {
                try {
                    throw new NotVerifiedTokenException("????????? ?????? ?????? ???????????????. ???????????? ????????? ??????????????????.");
                } catch (NotVerifiedTokenException ne) {
                    ReturnExceptionResponse.exceptionReturn(ne, response, 404);
                }
            }

        } catch (SignatureVerificationException se) {
            try {
                throw new NotVerifiedTokenException("???????????? ?????? ???????????? ???????????????.");
            } catch (NotVerifiedTokenException ne) {
                ReturnExceptionResponse.exceptionReturn(ne, response, 404);
            }
        } catch (JWTDecodeException je) {
            try {
                throw new NotVerifiedTokenException("???????????? ????????? ????????? ?????????.");
            } catch (NotVerifiedTokenException ne) {
                ReturnExceptionResponse.exceptionReturn(ne, response, 404);
            }
        } catch (TokenExpiredException te) {
            try {
                throw new NotVerifiedTokenException("???????????? ????????? ??????????????? ???????????????.");
            } catch (NotVerifiedTokenException ne) {
                ReturnExceptionResponse.exceptionReturn(ne, response, 404);
            }
        } catch (NotEqualsMemberException ne) {
            ReturnExceptionResponse.exceptionReturn(ne, response, 402);
        } catch (Exception e) {
            ReturnExceptionResponse.exceptionReturn(e, response, 403);
        }




    }

    @PostMapping("/oauth/save/member") // oauth ????????? ?????? ?????? ?????? -> ?????? ???????????? ????????? ???????????? ????????? ??????
    public ResponseEntity<ReturnLoginMemberInfo> saveApiMember(@RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String nic_name,
                                                               @RequestParam(required = false) String login_api,
                                                               @RequestParam(required = false) String file_src,
                                                               HttpServletResponse response) throws IOException {
        log.info("????????? email ??? {}", email);
        Member member = new Member();
        ProfileImg profileImg = new ProfileImg();
        if (email == null || login_api == null || email.equals("") || login_api.equals("")) {
            try {
                throw new EmailNullInputException("????????? ?????? ?????? ???????????????.");
            } catch (EmailNullInputException ee) {
                ReturnExceptionResponse.exceptionReturn(ee, response,401);
                return null;
            }
        }
        try {
            member = memberService.getMemberByEmail(email);
        } catch (UsernameNotFoundException ue) {
            log.error(ue.getMessage());
            member = Member.builder().email(email)
                    .nic_name(nic_name)
                    .role(Role.USER).build();
            try {
                member.setLoginApiSwitch(login_api);
            } catch (BadRequestLoginApiException be) {
                ReturnExceptionResponse.exceptionReturn(be, response,401);
                return null;
            }
            memberService.saveMember(member);
            member = memberService.getMemberByEmail(email);
        }

        member.settingNic_name(nic_name);
        try {
            member.setLoginApiSwitch(login_api);
        } catch (BadRequestLoginApiException be) {
            ReturnExceptionResponse.exceptionReturn(be, response,401);
            return null;
        }


//        Member findMember = memberService.getMemberByEmail(email);

        try {
            profileImg = profileService.getProfileImgByMember(member);
        } catch (ProfileNotFoundException pe) {
            log.error(pe.getMessage());
        }

        profileImg.settingMember(member);
        profileImg.modifyFileInfo(new File_info(file_src, login_api));
        ProfileImg savedProfileImg = profileService.saveProfile(profileImg);

        String src = "";
        if (member.getLogin_api() == LoginApi.EMAIL) {
            src = FilePath.BASIC_FILE_PATH + profileImg.getFile_info().getFile_src();
        } else {
            src = profileImg.getFile_info().getFile_src();
        }

        ReturnLoginMemberInfo returnMember = ReturnLoginMemberInfo.builder().id(member.getId())
                .email(member.getEmail())
                .nic_name(member.getNic_name())
                .login_api(member.getLogin_api().getKey())
                .role(member.getRole().getKey())
                .profileImg(src)
                .build();


        log.info("find Member is {}, Profile is {}", member.getNic_name(), savedProfileImg.getFile_info().getFile_src());
        log.info("Return member is {}", returnMember);
        return ResponseEntity.ok().body(returnMember);
    }

}
