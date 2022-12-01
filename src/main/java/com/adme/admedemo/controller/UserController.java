package com.adme.admedemo.controller;

import com.adme.admedemo.dto.*;
import com.adme.admedemo.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Api("User Controller API") // Swagger 에서 확인할 컨트롤러 이름
@RequestMapping("/sign-api")
public class UserController {

    private final SignService signService;

    @Autowired
    public UserController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value = "/sign-up")
    public SignUpResultDto signUp(@RequestBody @ApiParam(value = "회원가입 정보", required = true) SignUpRequestDto signUpRequestDto) {
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}", signUpRequestDto.getUid(), signUpRequestDto.getName());

        SignUpResultDto signUpResultDto = signService.signUp(signUpRequestDto);

        log.info("[signUp] 회원가입을 완료했습니다. id : {}", signUpRequestDto.getUid());

        return signUpResultDto;
    }

    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(@RequestBody @ApiParam(value = "로그인 정보", required = true) SignInRequestDto signInRequestDto, HttpServletResponse response) throws RuntimeException {
        log.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", signInRequestDto.getId());

        SignInResultDto signInResultDto = signService.signIn(signInRequestDto);

        if (signInResultDto.getCode() == 0) {
            log.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}",
                    signInRequestDto.getId(), signInResultDto.getToken());
        }

        log.info("[getSignInResult] 쿠키 생성"); //쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다. (브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("CookieId", signInRequestDto.getId());
        idCookie.setMaxAge(24 * 60 * 60); // 24시간 동안 유지
        idCookie.setPath("/"); // 모든 경로에서 접근 가능
        response.addCookie(idCookie);

        return signInResultDto;
    }

    @PostMapping(value = "/cookie")
    public void cookieList(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            log.info("[getCookie]CookieName"+c.getName());
            log.info("[getCookie]CookieValue"+c.getValue()+"\n");
        }
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
