package com.springboot.recipestep.mapper;

import com.springboot.recipestep.dto.RecipeStepDto;
import com.springboot.recipestep.entity.RecipeStep;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeStepMapper {
    RecipeStep recipeStepPostDtoToRecipeStep(RecipeStepDto.post recipeStepPostDto);
    RecipeStep recipeStepPatchDtoToRecipeStep(RecipeStepDto.patch recipeStepPatchDto);
    List<RecipeStepDto.Response> recipeStepsToRecipeStepResponseDtos(List<RecipeStep> recipeSteps);
    RecipeStepDto.Response recipeStepToRecipeStepResponseDto(RecipeStep recipeStep);
}