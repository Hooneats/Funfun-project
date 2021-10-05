package com.kosmo.funfunhaejwo.jpa.controller.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.domain.semi.LoginApi;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Role;
import com.kosmo.funfunhaejwo.jpa.exception.*;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import com.kosmo.funfunhaejwo.jpa.service.ProfileService;
import com.kosmo.funfunhaejwo.security.config.dao.JWTGenerator;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;


/**
 * Http Status Error code
 * 양식에 맞지 않은 요청양식 : 401
 * 요청 양식은 맞지만 찾을 수 없는 데이터인경우 : 402
 * URL 은 유효하지만 사용자의 요청을 이행 할 수 없음 : 403
 * JWT 토큰관련 유효하지 않은 토큰 : 404
 * 이미 존재하는 회원인 경우(회원가입 시) : 405
 * */


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
class OauthLoginController {

    private final MemberService memberService;
    private final ProfileService profileService;

    @PostMapping("/oauth/get/tokens") // oauth 로그인 시에 DB 에서 회원있나 확인후 토큰발급
    public void apiLogin(@RequestParam(required = false) String email,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        try {
            if (email == null) {
                throw new EmailNullInputException("적절한 양식이 아닙니다");
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

    @PostMapping("/oauth/get/tokens/refresh_token") // 리프레시 토큰으로 새로운 엑세스 토큰 요첟
    public void getAccessTokenFromRefreshToken(@RequestParam(required = false) String email,
                                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (email == null) {
                try {
                    throw new EmailNullInputException("양식에 맞지 않은 요청입니다.");
                } catch (EmailNullInputException ee) {
                    ReturnExceptionResponse.exceptionReturn(ee, response, 401);
                }
            }
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            log.info("# authorizationHeader is {}", authorizationHeader);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                log.info("# 권한체크 전 email {}", email);
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("KosmoFunFunHaeJow".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                log.info("# 권한체크 후 username {}", username);

                Member memberByEmail = memberService.getMemberByEmail(username);
                if (!memberByEmail.getEmail().equals(email)) {
                    throw new NotEqualsMemberException("알맞지 않은 회원의 요청입니다");
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
                    throw new NotVerifiedTokenException("양식에 맞지 않은 요청입니다. 리프레시 토큰을 확인해주세요.");
                } catch (NotVerifiedTokenException ne) {
                    ReturnExceptionResponse.exceptionReturn(ne, response, 404);
                }
            }

        } catch (SignatureVerificationException se) {
            try {
                throw new NotVerifiedTokenException("유효하지 않은 리프레시 토큰입니다.");
            } catch (NotVerifiedTokenException ne) {
                ReturnExceptionResponse.exceptionReturn(ne, response, 404);
            }
        } catch (JWTDecodeException je) {
            try {
                throw new NotVerifiedTokenException("리프레시 토큰을 확인해 주세요.");
            } catch (NotVerifiedTokenException ne) {
                ReturnExceptionResponse.exceptionReturn(ne, response, 404);
            }
        } catch (TokenExpiredException te) {
            try {
                throw new NotVerifiedTokenException("리프레시 토큰의 유효기간이 지났습니다.");
            } catch (NotVerifiedTokenException ne) {
                ReturnExceptionResponse.exceptionReturn(ne, response, 404);
            }
        } catch (NotEqualsMemberException ne) {
            ReturnExceptionResponse.exceptionReturn(ne, response, 402);
        } catch (Exception e) {
            ReturnExceptionResponse.exceptionReturn(e, response, 403);
        }




    }

    @PostMapping("/oauth/save/member") // oauth 로그인 시에 회원 저장 -> 먼저 찾아보고 있으면 업데이트 없으면 저장
    public ResponseEntity<ReturnLoginMemberInfo> saveApiMember(@RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String nic_name,
                                                               @RequestParam(required = false) String login_api,
                                                               @RequestParam(required = false) String file_src,
                                                               HttpServletResponse response) throws IOException {
        log.info("들어온 email 값 {}", email);
        Member member = new Member();
        ProfileImg profileImg = new ProfileImg();
        if (email == null || login_api == null || email.equals("") || login_api.equals("")) {
            try {
                throw new EmailNullInputException("양식에 맞지 않은 요청입니다.");
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

        ReturnLoginMemberInfo returnMember = ReturnLoginMemberInfo.builder().id(member.getId())
                .email(member.getEmail())
                .nic_name(member.getNic_name())
                .login_api(member.getLogin_api().getKey())
                .role(member.getRole().getKey())
                .profileImg(savedProfileImg.getFile_info().getFile_src())
                .build();


        log.info("find Member is {}, Profile is {}", member.getNic_name(), savedProfileImg.getFile_info().getFile_src());
        log.info("Return member is {}", returnMember);
        return ResponseEntity.ok().body(returnMember);
    }

}

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fun")
class FunMemberLoginController {
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
                .profileImg(findProfile.getFile_info().getFile_src())
                .build();

        log.info(returnMemberInfo.toString());
        return ResponseEntity.ok().body(returnMemberInfo);

    }

}

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/join")
class JoinController {

    private final MemberService memberService;

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
                .password(password)
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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/find")
class FindController {

    private final MemberService memberService;

    @PostMapping("/id")
    public ResponseEntity<?> findIdFromPhone(@RequestParam(required = false) String phone,
                                             HttpServletResponse response) throws IOException {
        List<String> findEmail = new ArrayList<>();
        if (phone == null) {
            try {
                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
            } catch (EmailNullInputException en) {
                ReturnExceptionResponse.exceptionReturn(en,response,401);
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

}



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
class ReturnLoginMemberInfo {
    private Long id;
    private String email;
    private String nic_name;
    private String login_api;
    private String role;
    private String  profileImg;
}