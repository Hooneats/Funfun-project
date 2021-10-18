//package com.kosmo.funfunhaejwo.jpa.controller.login;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.kosmo.funfunhaejwo.jpa.domain.Member;
//import com.kosmo.funfunhaejwo.jpa.exception.DoesNotSendMessageException;
//import com.kosmo.funfunhaejwo.jpa.exception.EmailNullInputException;
//import com.kosmo.funfunhaejwo.jpa.exception.ReturnExceptionResponse;
//import com.kosmo.funfunhaejwo.jpa.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import net.minidev.json.JSONArray;
//import net.minidev.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
///**
// * 유효코드 메시지 전송에 실패 : 405
// * 일치하지 않는 인증 코드 : 406
// * 인증코드 시간이 만료된 경우 : 407
// */
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/message")
//public class SendMessageController {
//
//    private final MemberService memberService;
//    private final JavaMailSender mailSender;
//
//    @Value("${naver.accessKey}")
//    private String accessKey;
//    @Value("${naver.secretKey}")
//    private String secretKey;
//    @Value("${naver.serviceId}")
//    private String serviceId;
//    @Value("${naver.phonNum}")
//    private String myPhone;
//
//    @PostMapping("/phone/verify")
//    public ResponseEntity<?> sendMessage(@RequestParam String phoneNum,
//                                         HttpServletRequest request,
//                                         HttpServletResponse redirectResponse) throws IOException {
//
//
//
//
//        log.info("메시지 전송전 들어온 값 : {} , {}", phoneNum);
//        try {
//            if (!phoneNum.substring(0, 3).equals("010")) {
//                throw new Exception();
//            }
//
//            String hostNameUrl = "https://sens.apigw.ntruss.com";            // 호스트 URL
//            String requestUrl = "/sms/v2/services/";                        // 요청 URL
//            String requestUrlType = "/messages";                            // 요청 URL
////            String accessKey = "mqTCwdHkffbYc5cinHy1";                     // 네이버 클라우드 플랫폼 회원에게 발급되는 개인 인증키
////            String secretKey = "hdgmZSyU02gvF8MPoduLBiNq05nXLee5qGANh0Md"; // 2차 인증을 위해 서비스마다 할당되는 service secret
////            String serviceId = "ncp:sms:kr:272279477613:funfun"; // 프로젝트에 할당된 SMS 서비스 ID
//            String method = "POST";                                            // 요청 method
//            String timestamp = Long.toString(System.currentTimeMillis());    // current timestamp (epoch)
//            requestUrl += serviceId + requestUrlType;
//            String apiUrl = hostNameUrl + requestUrl;
//
//            // JSON 을 활용한 body data 생성
//
//            JSONObject bodyJson = new JSONObject();
//            JSONObject toJson = new JSONObject();
//            JSONArray toArr = new JSONArray();
//
//            Random code = new Random();
//            String verifyNum = String.valueOf(code.nextInt(100000));
//
////        toJson.put("subject","인증코드 넘버입니다.");				// 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
//            toJson.put("content", "인증코드 번호 : " + verifyNum);        // 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
//            toJson.put("to", phoneNum);                    // 수신번호 목록  * 최대 50개까지 한번에 전송할 수 있습니다.
//            toArr.add(toJson);
//
//            bodyJson.put("type", "SMS");                // 메시지 Type (sms | lms)
//            bodyJson.put("contentType", "COMM");            // 메시지 내용 Type (AD | COMM) * AD: 광고용, COMM: 일반용 (default: COMM) * 광고용 메시지 발송 시 불법 스팸 방지를 위한 정보통신망법 (제 50조)가 적용됩니다.
//            bodyJson.put("countryCode", "82");        // 국가 전화번호
//            bodyJson.put("from", myPhone);                // 발신번호 * 사전에 인증/등록된 번호만 사용할 수 있습니다.
////        bodyJson.put("subject","");				// 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
//            bodyJson.put("content", "인증코드 번호 : " + verifyNum);        // 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
//            bodyJson.put("messages", toArr);
//
//
//            String body = bodyJson.toJSONString();
//
//            log.info("Send Message, This Content : {}", body);
//
//
//            URL url = new URL(apiUrl);
//
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setUseCaches(false);
//            con.setDoOutput(true);
//            con.setDoInput(true);
//            con.setRequestProperty("content-type", "application/json");
//            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
//            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
//            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
//            con.setRequestMethod(method);
//            con.setDoOutput(true);
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//
//            wr.write(body.getBytes());
//            wr.flush();
//            wr.close();
//
//            int responseCode = con.getResponseCode();
//            BufferedReader br;
//            if (responseCode == 202) { // 정상 호출
//                log.info("Send Message And Return Response Code Is {}", responseCode);
//                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            } else {  // 에러 발생
//                log.error("Does Not Send Message And Return Response Code Is {}", responseCode);
//                throw new Exception();
//            }
//
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
//            }
//            br.close();
//
//            log.info("Send Message And Return Response Content Is {}", response.toString());
//
//            Algorithm algorithm = Algorithm.HMAC256("KosmoFunFunHaeJow".getBytes());
//            String verifyCode = JWT.create()
//                    .withSubject("code")
//                    .withExpiresAt(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
//                    .withIssuer(request.getRequestURL().toString())
//                    .withClaim("code", String.valueOf(verifyNum))
//                    .sign(algorithm);
//
//            return ResponseEntity.ok().body(verifyCode);
//
//        } catch (Exception e) {
//            try {
//                throw new DoesNotSendMessageException("입력하신 번호로 인증번호를 전송할 수 없습니다.");
//            } catch (DoesNotSendMessageException ex) {
//                log.error(ex.getMessage());
//                ReturnExceptionResponse.exceptionReturn(ex, redirectResponse, 405);
//                return null;
//            }
//        }
//    }
//
//
//    public static String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
//        String space = " ";                    // one space
//        String newLine = "\n";                 // new line
//
//
//        String message = new StringBuilder()
//                .append(method)
//                .append(space)
//                .append(url)
//                .append(newLine)
//                .append(timestamp)
//                .append(newLine)
//                .append(accessKey)
//                .toString();
//
//        SecretKeySpec signingKey;
//        String encodeBase64String;
//        try {
//
//            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
//            Mac mac = Mac.getInstance("HmacSHA256");
//            mac.init(signingKey);
//            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
//            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
//        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
//            // TODO Auto-generated catch block
//            encodeBase64String = e.toString();
//        }
//
//
//        return encodeBase64String;
//    }
//
//    @PostMapping(value = "/email/send")
//    public ResponseEntity<?> sendEmailAction(@RequestParam(required = false) String email,
//                                             HttpServletRequest request,
//                                             HttpServletResponse response) throws Exception {
//        if (email == null) {
//            try {
//                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
//            } catch (EmailNullInputException en) {
//                ReturnExceptionResponse.exceptionReturn(en, response, 401);
//                return null;
//            }
//        }
//        Member findMember = new Member();
//        try {
//            findMember = memberService.getMemberByEmail(email);
//        } catch (UsernameNotFoundException ue) {
//            ReturnExceptionResponse.exceptionReturn(ue, response, 402);
//            return null;
//        }
//        String username = findMember.getNic_name();
//        String findEmail = findMember.getEmail();
//
//        Random code = new Random();
//        int randomCode = code.nextInt(100000);
//
//
//        try {
//
//            MimeMessage msg = mailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");
//
//            messageHelper.setSubject(username + "님 인증 코드 입니다.");
//            // 여기서는 보간 문법이 안된다...보간 사용시 코드만 보내짐...
//            messageHelper.setText("인증 코드 : " + String.valueOf(randomCode));
//            messageHelper.setTo(findEmail);
//            msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
//            mailSender.send(msg);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            ReturnExceptionResponse.exceptionReturn(e, response, 405);
//            return null;
//        }
//
//        Algorithm algorithm = Algorithm.HMAC256("KosmoFunFunHaeJow".getBytes());
//        String verifyCode = JWT.create()
//                .withSubject("code")
//                .withExpiresAt(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
//                .withIssuer(request.getRequestURL().toString())
//                .withClaim("code", String.valueOf(randomCode))
//                .sign(algorithm);
//
//        return ResponseEntity.ok().body(verifyCode);
//    }
//
//    @PostMapping("/verify/code")
//    public ResponseEntity<?> isCodeEqual(@RequestParam(required = false) String issuerCode,
//                                         @RequestParam(required = false) String inputCode,
//                                         HttpServletResponse response) throws IOException {
//        if (issuerCode.trim() == null || inputCode.trim() == null) {
//            try {
//                throw new EmailNullInputException("양식에 맞지 않는 요청입니다.");
//            } catch (EmailNullInputException en) {
//                ReturnExceptionResponse.exceptionReturn(en, response, 401);
//                return null;
//            }
//        }
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("KosmoFunFunHaeJow".getBytes());
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT decodedJWT = verifier.verify(issuerCode);
//            String decodeIssuerCode = decodedJWT.getClaim("code").toString();
//            int lastIndex = decodeIssuerCode.lastIndexOf('"');
//            String decode = decodeIssuerCode.substring(1, lastIndex);
//            log.info("받은 발급 코드 : {}", decode);
//            log.info("사용자에게 받은 코드 : {}", inputCode);
//            if (!decode.equals(inputCode)) {
//                try {
//                    throw new Exception("일치하지 않는 인증코드입니다.");
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                    ReturnExceptionResponse.exceptionReturn(e, response, 406);
//                    return null;
//                }
//            }
//        } catch (JWTDecodeException je) {
//            try {
//                throw new Exception("인증시간이 만료되었습니다.");
//            } catch (Exception e) {
//                ReturnExceptionResponse.exceptionReturn(e, response, 407);
//                return null;
//            }
//        }
//        return ResponseEntity.ok().body(true);
//
//    }
//}
