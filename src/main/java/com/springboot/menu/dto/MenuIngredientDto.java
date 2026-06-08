package com.springboot.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class MenuIngredientDto {
    @Getter
    @Setter
    public static class Post {
        @Schema(description = "재료 ID", example = "1", required = true)
        private long ingredientId;

        @Schema(description = "재료 타입 (주재료: MAIN, 조미료: SEASONING)", example = "MAIN", required = true)
        private String type;  // MAIN or SEASONING
    }

    @Getter
    @Setter
    public static class Patch {
        @Schema(description = "메뉴-재료 연결 ID", example = "10", required = true)
        private long menuIngredientId;

        @Schema(description = "메뉴 ID", example = "3", required = true)
        private long menuId;

        @Schema(description = "재료 ID", example = "7", required = true)
        private long ingredientId;
    }

    @Getter
    @Setter
    public static class Response {
        private long ingredientId;
        private String ingredientName;
        private String image;
        private String subCategory;
        private String type;
    }
}