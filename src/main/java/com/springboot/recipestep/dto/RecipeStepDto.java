package com.springboot.recipestep.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class RecipeStepDto {
    @Getter
    public static class post {
        @NotBlank(message = "ID는 공백이 아니어야 합니다.")
        @Schema(description = "레시피_스텝_아이디", example = "1")
        private long recipeStepId;

        @NotBlank(message = "스텝_이름은 공백이 아니어야 합니다.")
        @Schema(description = "레시피_스텝_이름", example = "재료준비")
        private String title;
    }

    @Getter
    @Setter
    public static class patch {
        private long recipeStepId;

        @NotBlank(message = "스텝_이름은 공백이 아니어야 합니다.")
        @Schema(description = "레시피_스텝_이름", example = "재료준비")
        private String title;
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private long recipeStepId;
        private String title;
    }
}