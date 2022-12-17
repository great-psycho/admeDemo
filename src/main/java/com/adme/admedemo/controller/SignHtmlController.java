package com.adme.admedemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SignHtmlController {

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signOn")
    public String logon(){
        log.info("===signOn Page===");
        return "loginOn";
    }
}
