package com.adme.admedemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "회원가입 정보", description = "아이디, 비밀번호")
public class SignInRequestDto {

    @ApiModelProperty(value = "아이디")
    private String id;

    @ApiModelProperty(value = "비밀번호")
    private String password;

}
