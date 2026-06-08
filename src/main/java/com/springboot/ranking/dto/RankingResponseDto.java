package com.springboot.ranking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RankingResponseDto {
    @Schema(description = "회원 ID", example = "1")
    private long memberId;
    @Schema(description = "회원 닉네임", example = "택택")
    private String nickName;
    @Schema(description = "회원 프로필 이미지 경로", example = "/images/profile.jpg")
    private String profileImagePath;
    @Schema(description = "랭킹 카운트 수(칭호 갯수, 게시글 갯수, 좋아요 갯수 등등)", example = "10")
    private long count;
    @Schema(description = "해당 카운트에 도달한 마지막 시점(동률일 경우 정렬에 필요합니다. \n 정렬되어 옵니다.", example = "2025-04-26 20:42:42.635224")
    private LocalDateTime firstRecipeCreatedAt; // 추가
}