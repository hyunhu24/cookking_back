package com.springboot.recipestepdetail.mapper;

import com.springboot.recipestepdetail.dto.RecipeStepDetailDto;
import com.springboot.recipestepdetail.entity.RecipeStepDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeStepDetailMapper {
    RecipeStepDetailDto.Response recipeStepDetailToRecipeStepDetailResponseDto(RecipeStepDetail recipeStepDetail);
    RecipeStepDetail recipeStepDetailPostDtoToRecipeStepDetail(RecipeStepDetailDto.Post post);
    RecipeStepDetail recipeStepDetailPatchDtoToRecipeStepDetail(RecipeStepDetailDto.Patch patch);
    List<RecipeStepDetailDto.Response> recipeStepDetailsToRecipeStepDetailResponseDtos(List<RecipeStepDetail> recipeStepDetails);
}
