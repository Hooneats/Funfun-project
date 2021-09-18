package com.kosmo.funfunhaejwo.jpa.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReturnExceptionResponse {

    public static void exceptionReturn(Throwable e, HttpServletResponse response, int statuscode) throws IOException {
        log.error("Error log {}", e.getMessage());
//        HttpServletResponse httpResponse = CrossHeader.corsHeader(response);
        response.setHeader("error", e.getMessage());
        response.setStatus(statuscode);

        Map<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

}
