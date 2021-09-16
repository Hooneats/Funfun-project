package com.kosmo.funfunhaejwo.jpa.controller.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.funfunhaejwo.jpa.domain.Like;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.ProfileImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Address;
import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Role;
import com.kosmo.funfunhaejwo.jpa.exception.EmailNullInputException;
import com.kosmo.funfunhaejwo.jpa.repository.ProfileImgRepo;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import com.kosmo.funfunhaejwo.jpa.service.MemberServiceImpl;
import com.kosmo.funfunhaejwo.jpa.service.ProfileService;
import com.kosmo.funfunhaejwo.jpa.service.ProfileServiceImpl;
import com.kosmo.funfunhaejwo.security.config.dao.CrossHeader;
import com.kosmo.funfunhaejwo.security.config.dao.JWTGenerator;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final MemberService memberService;
    private final ProfileService profileService;

    @PostMapping("/get/tokens")
    public void apiLogin(@RequestParam(required = false) String email,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        try {
            if (email == null) {
                throw new EmailNullInputException("적절한 양식이 아닙니다");
            } else {
                Member member = memberService.getMember(email);
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

                    HttpServletResponse httpResponse = CrossHeader.corsHeader(response);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                }
            }
        } catch (EmailNullInputException en) {
            log.error("Error log {}", en.getMessage());
            HttpServletResponse httpResponse = CrossHeader.corsHeader(response);
            response.setHeader("error", en.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());

            Map<String, String> error = new HashMap<>();
            error.put("error_message", en.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        } catch (UsernameNotFoundException ue) {
            log.error("Error log {}", ue.getMessage());
            HttpServletResponse httpResponse = CrossHeader.corsHeader(response);
            response.setHeader("error", ue.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());

            Map<String, String> error = new HashMap<>();
            error.put("error_message", ue.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

    }

    @PostMapping("/save/member")
    public ResponseEntity<ReturnMember> saveApiMember(@RequestParam String email,
                                                      @RequestParam(required = false) String nic_name,
                                                      @RequestParam(required = false) String login_api,
                                                      @RequestParam String file_src) {

        Member buildMember = Member.builder().email(email)
                .nic_name(nic_name)
                .role(Role.USER).build();
        buildMember.setLoginApiSwitch(login_api);
        Member member = memberService.saveMember(buildMember);

        ProfileImg build = ProfileImg.builder().member(member).file_info(new File_info(file_src, null)).build();
        profileService.saveProfile(build);

        Member findMember = memberService.getMember(email);

        ProfileImg findProfile = profileService.getMember(findMember);

        ReturnMember returnMember = ReturnMember.builder().id(findMember.getId())
                .email(findMember.getEmail())
                .nic_name(findMember.getNic_name())
                .login_api(findMember.getLogin_api().getKey())
                .role(findMember.getRole().getKey())
                .profileImg(findProfile.getFile_info().getFile_src())
                .likeList(findMember.getLikes()).build();
        log.info("find Member is {}, Profile is {}", findMember.getNic_name(), findProfile.getFile_info().getFile_src());
        log.info("Return member is {}", returnMember);
        return ResponseEntity.ok().body(returnMember);
    }
}

@Builder
@Data
class ReturnMember {
    private Long id;
    private String email;
    private String nic_name;
    private String login_api;
    private String role;
    private String  profileImg;
    private List<Like> likeList = new ArrayList<>();
}