package com.springboot.recipeboard.dto;

import com.springboot.ingredient.dto.IngredientDto;
import com.springboot.recipeboard.entity.RecipeBoard;
import com.springboot.recipestepdetail.dto.RecipeStepDetailDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class RecipeBoardDto {
    @Getter
    public static class Post {
        @Schema(description = "레시피 게시글 제목", example = "존나 맛있는 공기밥 레시피")
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        @Size(min = 1, max = 20, message = "제목은 1자 이상 20자 이내여야 합니다.")
        @Pattern(
                regexp = "^(?!\\s)(?!.*\\s{2,}).*$",
                message = "제목은 공백으로 시작하거나 연속된 공백이 포함될 수 없습니다."
        )
        private String title;

        @Schema(description = "레시피 게시글 사진", example = "imageURL")
        @NotBlank(message = "대표 사진은 필수입니다.")
        private String image;

        @Schema(description = "레시피 공개 상태", example = "RECIPE_PUBLIC")
        private RecipeBoard.RecipeStatus recipeStatus;

        @Schema(description = "레시피 단계 리스트")
        private List<RecipeBoardStepDto.Post> recipeBoardSteps;

        @Schema(description = "메뉴 이름 (기존 메뉴가 없을 경우 등록)", example = "김치찌개")
        private String menuName;

        @Schema(description = "메뉴 카테고리 ID", example = "3")
        private long menuCategoryId;

        @Schema(description = "메뉴 서브 카테고리", example = "찌개")
        private String menuSubCategory;

        @Schema(description = "레시피에 들어가는 재료 리스트")
        private List<IngredientDto.IdRequest> ingredients;
    }

    @Getter
    @Setter
    public static class Patch {
        private long recipeBoardId;

        @Schema(description = "레시피 게시글 제목", example = "존나 맛있는 공기밥 레시피")
        @Size(min = 1, max = 20, message = "제목은 1자 이상 20자 이내여야 합니다.")
        @Pattern(
                regexp = "^(?!\\s)(?!.*\\s{2,}).*$",
                message = "제목은 공백으로 시작하거나 연속된 공백이 포함될 수 없습니다."
        )
        private String title;

        @Schema(description = "레시피 대표 사진 URL", example = "https://updated-image-url.jpg")
        private String image;

        @Schema(description = "레시피 상태", example = "RECIPE_PRIVATE")
        private RecipeBoard.RecipeStatus recipeStatus;

        @Schema(description = "레시피 단계 리스트 (본문 포함)")
        private List<RecipeBoardStepDto.Post> recipeBoardSteps;

        @Schema(description = "레시피에 사용된 재료 리스트")
        private List<IngredientDto.IdRequest> ingredients;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public static class Response {
        private Long recipeBoardId;
        private long memberId;
        private String title;
        private String image;
        private long menuId;
        private RecipeBoard.RecipeStatus recipeStatus;
        private RecipeBoard.RecipeBoardStatus recipeBoardStatus;
        private String nickName; // member.getNickName()이랑 매핑 필요
        private LocalDateTime createdAt;
        private List<RecipeStep> recipeStep;
        private List<IngredientDto.Response> mainIngredients;
        private List<IngredientDto.Response> seasoningIngredients;
        private long likeCount; // 좋아요 수
        private boolean liked; // 좋아요 여부
        private boolean bookmarked;
    }

    @NoArgsConstructor
    @Setter
    @Getter
    public static class RecipeStep {
        @Schema(description = "레시피 단계 ID", example = "1")
        private long recipeStepId;

        @Schema(description = "레시피 단계 순서", example = "1")
        private int stepOrder;

        @Schema(description = "레시피 단계 제목", example = "재료 준비")
        private String title;

        @Schema(description = "레시피 단계 리스트")
        private List<RecipeStepDetailDto.Response> recipeBoardSteps;
    }
}