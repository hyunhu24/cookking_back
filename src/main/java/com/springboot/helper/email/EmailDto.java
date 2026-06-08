package com.springboot.helper.email;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmailDto {

    @Getter
    @NoArgsConstructor
    public static class Request {
        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        @Schema(description = "이메일 주소", example = "tjsk2222@gmail.com")
        private String email;
    }

    @Getter
    @NoArgsConstructor
    public static class Confirm {
        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        @Schema(description = "이메일 주소", example = "tjsk2222@gmail.com")
        private String email;

        @NotBlank(message = "인증번호는 공백일 수 없습니다.")
        @Size(min = 6, max = 6, message = "인증번호는 6자리여야 합니다.")
        @Schema(description = "이메일 인증번호", example = "123456")
        private String code;
    }
}
