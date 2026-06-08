package com.springboot.recipeboard.dto;

import com.springboot.recipestepdetail.dto.RecipeStepDetailDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipeBoardStepDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @Schema(description = "레시피 단계 ID (기존 단계일 경우)", example = "1")
        private long recipeStepId;

        @Schema(description = "레시피 단계 순서", example = "1", required = true)
        private int stepOrder;

        @Schema(description = "레시피 단계 상세 리스트", required = true)
        private List<RecipeStepDetailDto.Post> recipeStepDetails;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        @Schema(description = "레시피 단계 ID", example = "1", required = true)
        private long recipeStepId;

        @Schema(description = "레시피 단계 순서", example = "1")
        private int stepOrder;

        @Schema(description = "레시피 단계 상세 리스트")
        private List<RecipeStepDetailDto.Patch> recipeStepDetails;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class response {
        private long recipeStepId;
        private int stepOrder;
        private List<RecipeStepDetailDto.Response> recipeStepDetails;
    }
}