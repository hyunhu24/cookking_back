package com.springboot.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginDto {
    @Schema(description = "사용자 아이디", example = "tjsk2222")
    private String id;

    @Schema(description = "사용자 비밀번호", example = "xorqnd123!")
    private String password;
}
