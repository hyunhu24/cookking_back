package com.springboot.member.dto;

import com.springboot.member.entity.Member;
import com.springboot.title.dto.TitleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

public class MemberDto {
    @Getter
    public static class Id{
        @NotBlank(message = "ID는 공백이 아니어야 합니다.")
        @Schema(description = "사용자 아이디", example = "tjsk2222")
        private String loginId;
    }

    @Getter
    public static class Name{
        @NotBlank(message = "닉네임은 공백이 아니어야 합니다.")
        @Pattern(regexp = "^(?!\\s)(?!.*\\s{2,})(?!.*[~!@#$%^&*()_+=|<>?:{}\\[\\]\"';,.\\\\/`])[^\\s]{1,8}(?<!\\s)$",
                message = "닉네임은 공백 없이 8자 이내, 특수문자를 포함하지 않아야 합니다.")
        @Schema(description = "사용자 닉네임", example = "택택")
        private String nickName;
    }

    @Getter
    public static class Phone{
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        @Schema(description = "사용자 전화번호", example = "010-1111-2222")
        private String phoneNumber;
    }

    @Getter
    public static class Post {
        @NotBlank(message = "ID는 공백이 아니어야 합니다.")
        @Schema(description = "사용자 아이디", example = "tjsk2222")
        private String loginId;

        @NotBlank(message = "이메일은 공백이 아니어야 합니다.")
        @Email(message = "이메일 형식을 잘못 입력했습니다.")
        @Schema(description = "사용자 이메일", example = "tjsk2222@gmail.com")
        private String email;

        @NotBlank
        @Pattern(regexp = "^(?=(?:.*[A-Za-z]){6,})(?=.*\\d)(?=(?:[^%$#@!*]*[%$#@!*])+)[A-Za-z\\d%$#@!*]{8,20}$",
                message = "비밀번호는 8~20자 영문(최소 6자), 숫자, 특수문자(%,$,#,@,!,*) 1자 이상을 조합해야 합니다.")
        @Schema(description = "사용자 비밀번호", example = "xorqnd123!")
        private String password;

        @NotBlank(message = "닉네임은 공백이 아니어야 합니다.")
        @Pattern(regexp = "^(?!\\s)(?!.*\\s{2,})(?!.*[~!@#$%^&*()_+=|<>?:{}\\[\\]\"';,.\\\\/`])[^\\s]{1,8}(?<!\\s)$",
                message = "닉네임은 공백 없이 8자 이내, 특수문자를 포함하지 않아야 합니다.")
        @Schema(description = "사용자 닉네임", example = "택택")
        private String nickName;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        @Schema(description = "사용자 전화번호", example = "010-1111-2222")
        private String phoneNumber;

        @Positive
        @Schema(description = "프로필 이미지 ID", example = "1")
        private long profileImageId;
    }

    @Getter
    public static class Patch{
        private long memberId;

        @Setter
        @NotBlank(message = "닉네임은 공백이 아니어야 합니다.")
        @Pattern(regexp = "^(?!\\s)(?!.*\\s{2,})(?!.*[~!@#$%^&*()_+=|<>?:{}\\[\\]\"';,.\\\\/`])[^\\s]{1,8}(?<!\\s)$",
                message = "닉네임은 공백 없이 8자 이내, 특수문자를 포함하지 않아야 합니다.")
        @Schema(description = "사용자 닉네임", example = "어쩌고저쩌고")
        private String nickName;

        @Schema(description = "사용자 이미지 ID", example = "1")
        private long profileImageId;

        @Schema(description = "사용자 전화번호", example = "010-1111-2222")
        private long phoneNumber;

        @Schema(description = "사용자 착용 칭호 ID", example = "1")
        private long activeTitleId;

    }

    @Getter
    public static class Delete {
        @Schema(description = "회원 이메일", example = "tjsk2222@gmail.com")
        @NotBlank(message = "이메일은 공백이 아니어야 합니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @Schema(description = "회원 비밀번호", example = "xorqnd123!")
        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        private String password;
    }

    @Getter
    public static class FindId {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email
        @Schema(description = "회원 이메일", example = "tjsk2222@gmail.com")
        private String email;

        @NotBlank(message = "전화번호는 필수입니다.")
        @Schema(description = "회원 전화번호", example = "010-1111-2222")
        private String phoneNumber;
    }

    @Getter
    public static class ResetPassword {
        @NotBlank(message = "로그인 ID는 필수입니다.")
        @Schema(description = "회원 로그인 ID", example = "tjsk2222")
        private String loginId;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email
        @Schema(description = "회원 이메일", example = "tjsk2222@gmail.com")
        private String email;

        @NotBlank(message = "새 비밀번호는 필수입니다.")
        @Pattern(
                regexp = "^(?=(?:.*[A-Za-z]){6,})(?=.*\\d)(?=(?:[^%$#@!*]*[%$#@!*])+)[A-Za-z\\d%$#@!*]{8,20}$",
                message = "비밀번호는 8~20자 영문(최소 6자), 숫자, 특수문자(%,$,#,@,!,*) 1자 이상을 조합해야 합니다."
        )
        @Schema(description = "새 비밀번호", example = "andrprnfma1*")
        private String newPassword;
    }


    @NoArgsConstructor
    @Setter
    @Getter
    public static class Response {
        private long memberId;
        private String loginId;
        private String email;
        private String nickName;
        private String phoneNumber;
        private int ricePoint;
        private Member.MemberStatus memberStatus;
        private String profileImagePath;
        private List<TitleDto.Response> titles;
        private long activeProfileId;
        private long activeTitleId;
    }
}