package com.bit.ww.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

//로그인 전용 DTO
@Getter
public class LoginDTO {
    @ApiModelProperty(example = "id")
    private String id;
    @ApiModelProperty(example = "password")
    private String pw;
}
