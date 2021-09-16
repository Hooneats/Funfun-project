package com.kosmo.funfunhaejwo.jpa.controller;

import com.kosmo.funfunhaejwo.jpa.controller.dao.EXMemberInfoDao;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class EXMemberController {

    private final MemberService memberService;

    @GetMapping("/memberinfo")
    public ResponseEntity<EXMemberInfoDao> MemberInfo(@RequestParam String email) {
        Member member = memberService.getMember(email);
        EXMemberInfoDao EXMemberInfoDao = new EXMemberInfoDao();
        EXMemberInfoDao returnMember = EXMemberInfoDao.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getNic_name()).build();
        return ResponseEntity.ok().body(returnMember);
    }

//    @PostMapping("/like/update")
//    public void okLikeUpdate(@RequestParam("product_like_count") String  product_like_count, HttpServletResponse response) throws IOException {
//        log.info("# LikeCount from front : {}", product_like_count);
////        도착하는것은 확인함 저장하는 로직 필요 -> 여기 헤더  로직 필요없다 필터로 적용했기에
//        response.setStatus(HttpStatus.OK.value());
//        response.setHeader("Access-Control-Allow-Origin", "*");     //허용할 Origin(요청 url) : "*" 의 경우 모두 허용
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");     //허용할 request http METHOD : POST, GET, DELETE, PUT
//        response.setHeader("Access-Control-Max-Age", "3600");     //브라우저 캐시 시간(단위: 초) : "3600" 이면 최소 1시간 안에는 서버로 재요청 되지 않음
//        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");    //요청 허용 헤더(ajax : X-Requested-With)
//
//        new ObjectMapper().writeValue(response.getOutputStream(),"도착테스트 완료");
//    }

//  참고 : https://great-developer.tistory.com/59
    @PostMapping("/like/update")
    public ResponseEntity<?> okLikeUpdate(@RequestParam("like_up") Boolean  likeUp) {
        log.info("# LikeCount from front : {}", likeUp);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @PostMapping("/fundingregist")
    public ResponseEntity<?> okRegistFunding(@RequestParam Long member_id,
                                             @RequestParam Long product_id,
                                             @RequestParam String  funding_title,
                                             @RequestParam String  funding_create_time,
                                             @RequestParam String  funding_expired_time,
                                             @RequestParam Integer funding_target_money
                                             ) {
        FundingDataForRegist fundingDataForRegist = new FundingDataForRegist(member_id, product_id, funding_title, funding_create_time, funding_expired_time, funding_target_money);
        log.info("# fundingDataForRegist arrive : {}", fundingDataForRegist);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/fundingregist")
//    public ResponseEntity<?> okRegistFunding(@RequestBody FundingDataForRegist fundingDataForRegist
//    ) {
//        log.info("# fundingDataForRegist arrive : {}", fundingDataForRegist);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class FundingDataForRegist {
        Long  member_id;
        Long  product_id;
        String funding_title;
        String funding_create_time;
        String funding_expired_time;
        Integer funding_target_money;
    }

}

