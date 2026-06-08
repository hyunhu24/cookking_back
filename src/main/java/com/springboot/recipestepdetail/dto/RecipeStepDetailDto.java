package com.springboot.recipestepdetail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
public class RecipeStepDetailDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @Schema(description = "요리 순서", example = "1")
        private int stepOrder;

        @Schema(description = "요리 설명", example = "밥을 뜨겁게 데워줍니다.")
        @NotBlank(message = "요리 설명은 필수입니다.")
        private String description;

        @Schema(description = "요리 사진 URL", example = "https://image.url/step1.jpg")
        private String image;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Patch {
        @Schema(description = "레시피 단계 상세 ID", example = "1", required = true)
        private long recipeStepDetailId;

        @Schema(description = "요리 순서", example = "1")
        private int stepOrder;

        @Schema(description = "요리 설명", example = "밥을 뜨겁게 데워줍니다.")
        private String description;

        @Schema(description = "요리 사진 URL", example = "https://image.url/step1.jpg")
        private String image;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class Response {
        private long recipeStepDetailId;
        private int stepOrder;
        private String description;
        private String image;
    }
}
