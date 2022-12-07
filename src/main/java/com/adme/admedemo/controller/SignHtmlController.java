package com.adme.admedemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SignHtmlController {
    @GetMapping("/login")
    public String login(){
        log.info("View test page");
        return "login";
    }
}
