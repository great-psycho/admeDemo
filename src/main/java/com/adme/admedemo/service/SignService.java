package com.adme.admedemo.service;

import com.adme.admedemo.dto.SignInRequestDto;
import com.adme.admedemo.dto.SignInResultDto;
import com.adme.admedemo.dto.SignUpRequestDto;
import com.adme.admedemo.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(SignUpRequestDto signUpRequestDto);

    SignInResultDto signIn(SignInRequestDto signInRequestDto) throws RuntimeException;
}
