package com.springboot.ingredient.mapper;

import com.springboot.ingredient.dto.IngredientDto;
import com.springboot.ingredient.entity.Ingredient;
import com.springboot.ingredient.entity.MainIngredient;
import com.springboot.ingredient.entity.SeasoningIngredient;
import com.springboot.menu.dto.MenuDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    // Post DTO -> Entity (수동 분기 필요)
    default Ingredient ingredientPostDtoToIngredient(IngredientDto.Post postDto) {
        if (postDto == null) return null;

        Ingredient ingredient;

        String dtype = postDto.getDtype();
        if ("MAIN".equalsIgnoreCase(dtype)) {
            ingredient = new MainIngredient();
        } else if ("SEASONING".equalsIgnoreCase(dtype)) {
            ingredient = new SeasoningIngredient();
        } else {
            throw new IllegalArgumentException("Invalid dtype: " + dtype);
        }

        ingredient.setIngredientName(postDto.getIngredientName());
        ingredient.setImage(postDto.getImage());
        ingredient.setSubCategory(postDto.getSubCategory());

        return ingredient;
    }

    default Ingredient ingredientPatchDtoToIngredient(IngredientDto.Patch patchDto) {
        if (patchDto == null) return null;

        Ingredient ingredient;
        String dtype = patchDto.getDtype();

        if ("MAIN".equalsIgnoreCase(dtype)) {
            ingredient = new MainIngredient();
        } else if ("SEASONING".equalsIgnoreCase(dtype)) {
            ingredient = new SeasoningIngredient();
        } else {
            throw new IllegalArgumentException("Invalid dtype: " + dtype);
        }

        // 필드 값 세팅
        ingredient.setIngredientId(patchDto.getIngredientId());
        ingredient.setIngredientName(patchDto.getIngredientName());
        ingredient.setImage(patchDto.getImage());
        ingredient.setSubCategory(patchDto.getSubCategory());

        return ingredient;
    }

    default IngredientDto.Response ingredientToIngredientResponseDto(Ingredient ingredient) {
        if (ingredient == null) return null;

        String dtype;
        if (ingredient instanceof MainIngredient) {
            dtype = "MAIN";
        } else if (ingredient instanceof SeasoningIngredient) {
            dtype = "SEASONING";
        } else {
            throw new IllegalArgumentException("Unknown ingredient type: " + ingredient.getClass().getSimpleName());
        }

        return new IngredientDto.Response(
                ingredient.getIngredientId(),
                ingredient.getIngredientName(),
                ingredient.getImage(),
                ingredient.getSubCategory(),
                dtype
        );
    }

    List<IngredientDto.Response> ingredientsToIngredientResponseDtos(List<Ingredient> ingredients);

    default Ingredient ingredientDtoToIngredient(MenuDto.IngredientDto IngredientDto) {
        if (IngredientDto == null) return null;


        Ingredient ingredient = IngredientDto.getType().equals("MAIN") ?
                new MainIngredient() :
                new SeasoningIngredient();

        ingredient.setIngredientId(IngredientDto.getIngredientId());

        return ingredient;
    }

    default List<Ingredient> ingredientsDtoToIngredients(MenuDto.RecommendationsIngredientDto ingredientDtos) {
        if (ingredientDtos == null) return null;

        return ingredientDtos.getIngredients().stream()
                .map(this::ingredientDtoToIngredient)
                .collect(Collectors.toList());
    }
}