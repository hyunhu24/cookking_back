package com.springboot.ingredient.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class IngredientDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class IdRequest {
        @Schema(description = "재료 ID", example = "5", required = true)
        private long ingredientId;

        @Schema(description = "재료 타입 (주재료: MAIN, 조미료: SEASONING)", example = "MAIN", required = true)
        private String type;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @Schema(description = "재료 이름", example = "삼겹살", required = true)
        private String ingredientName;

        @Schema(description = "재료 이미지 URL", example = "https://example.com/ingredients/ingredient.jpg")
        private String image;

        @Schema(description = "서브 카테고리 (육류, 뿌리채소 등)", example = "육류")
        private String subCategory;

        @Schema(description = "재료 타입 (주재료: MAIN, 조미료: SEASONING)", example = "MAIN", required = true)
        private String dtype;  // "MAIN" or "SEASONING"
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        @Schema(description = "재료 ID", example = "1", required = true)
        private long ingredientId;

        @Schema(description = "재료 이름", example = "삼겹살", required = true)
        private String ingredientName;

        @Schema(description = "재료 이미지 URL", example = "https://example.com/ingredients/ingredient.jpg")
        private String image;

        @Schema(description = "서브 카테고리 (육류, 뿌리채소 등)", example = "육류")
        private String subCategory;

        // 보통 dtype은 수정 불가, 수정이 아닌 현재 상태를 나타내기 위해 사용
        @Schema(description = "재료 타입 (현재 상태만 표시, 수정 불가)", example = "MAIN", accessMode = Schema.AccessMode.READ_ONLY)
        private String dtype;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private long ingredientId;
        private String ingredientName;
        private String image;
        private String subCategory;
        private String dtype;
    }
}