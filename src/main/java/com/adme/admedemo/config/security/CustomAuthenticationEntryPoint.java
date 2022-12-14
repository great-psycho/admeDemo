package com.adme.admedemo.config.security;

import com.adme.admedemo.dto.EntryPointErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        log.info("[commence] 인증 실패로 response.sendError 발생");
//
//        EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
//        entryPointErrorResponse.setMsg("인증이 실패하였습니다.");
//
//        response.setStatus(401);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));
        log.info("[commence] 인증 실패로 response.sendError 발생");
        response.sendRedirect("/user/login");
    }
}
