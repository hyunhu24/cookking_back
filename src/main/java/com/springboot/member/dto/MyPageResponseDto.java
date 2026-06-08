package com.springboot.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MyPageResponseDto {
    private Long activeProfileId;
    private String profile;
    private String nickName;
    private String title;
    private int ricePoint;
}
