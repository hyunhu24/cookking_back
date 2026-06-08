package com.springboot.ranking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRankingResponseDto {
    @Schema(description = "게시글 작성 랭킹", example = "3, (null일 경우 순위 밖 유저)")
    private Integer recipeBoardRank;
    @Schema(description = "좋아요 랭킹", example = "2, (null일 경우 순위 밖 유저)")
    private Integer likeRank;
    @Schema(description = "북마크 랭킹", example = "1, (null일 경우 순위 밖 유저)")
    private Integer bookmarkRank;
    @Schema(description = "칭호 랭킹", example = "5, (null일 경우 순위 밖 유저)")
    private Integer titleRank;
}