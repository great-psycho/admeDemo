package com.adme.admedemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "회원가입 정보", description = "아이디, 비밀번호, 이름, 권한 유무, 권한 암호")
public class SignUpRequestDto {

    @ApiModelProperty(value = "아이디")
    private String uid;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "권한 유무")
    private boolean admin = false;

    @ApiModelProperty(value = "권한 암호")
    private String adminToken = "";
}
