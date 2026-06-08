package com.springboot.menu.dto;

import com.springboot.recipeboard.dto.RecipeBoardDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class MenuDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IngredientDto {
        @Schema(description = "재료 ID", example = "1")
        private long ingredientId;
        @Schema(description = "재료 타입", example = "MAIN, SEASONING")
        private String type;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecommendationsIngredientDto {
        @Schema(description = "재료 리스트")
        List<IngredientDto> ingredients;
    }


    @Getter
    @Setter
    public static class Post {
        @Schema(description = "메뉴 이름", example = "김치찌개", required = true)
        private String menuName;

        @Schema(description = "메뉴에 들어가는 재료 리스트", required = true)
        private List<MenuIngredientDto.Post> menuIngredientDtos;

        @Schema(description = "메뉴 카테고리 ID", example = "3", required = true)
        private long menuCategoryId;
    }
    @Getter
    @Setter
    public static class Patch {
        @Schema(description = "메뉴 ID", example = "1", required = true)
        private long menuId;

        @Schema(description = "메뉴 이름", example = "된장찌개")
        private String menuName;

        @Schema(description = "메뉴에 들어가는 재료 리스트")
        private List<MenuIngredientDto> menuIngredientDtos;

        @Schema(description = "메뉴 카테고리 ID", example = "2")
        private long menuCategoryId;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long menuId;
        private String menuName;
        private List<MenuIngredientDto.Response> menuIngredients;
        private MenuCategory category;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SimpleResponse {
        @Schema(description = "메뉴 ID", example = "1")
        private long menuId;
        @Schema(description = "메뉴 이름", example = "김치찌개")
        private String image;
        @Schema(description = "서브 카테고리 이름", example = "국물요리 (기타가 아닌경우 null)")
        private String subCategoryName;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class MenuCategory {
        @Schema(description = "메뉴 카테고리 ID", example = "1")
        private long menuCategoryId;
        @Schema(description = "메뉴 카테고리 이름", example = "한식")
        private String menuCategoryName;
        @Schema(description = "메뉴 카테고리 서브 카테고리 이름", example = "국물요리, (기타가 아닌경우 null)")
        private String menuSubCategory;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecommendationResponse {
        private Long menuId;
        private String menuName; // 공백 제거된 이름
        private String image;
    }

}