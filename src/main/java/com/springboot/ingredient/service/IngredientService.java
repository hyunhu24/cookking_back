package com.springboot.ingredient.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.ingredient.entity.Ingredient;
import com.springboot.ingredient.repository.IngredientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        verifyIngredientExists(ingredient.getIngredientName());

        return ingredientRepository.save(ingredient);
    }

    public Ingredient findIngredient(long ingredientId) {
        return findVerifiedIngredient(ingredientId);
    }

    public Page<Ingredient> findIngredients(int page, int size, String dtype) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "ingredientId");

        // 재료가 없을 경우, 타입 구분없이 전체를 반환(주, 양념 구분없이)
        if (dtype == null) {
            return ingredientRepository.findAll(pageable);
        }

        if ("MAIN".equalsIgnoreCase(dtype)) {
            return ingredientRepository.findMainIngredients(pageable);
        } else if ("SEASONING".equalsIgnoreCase(dtype)) {
            return ingredientRepository.findSeasoningIngredients(pageable);
        } else {
            throw new BusinessLogicException(ExceptionCode.INGREDIENT_TYPE_NOT_FOUND);
        }
    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient findIngredient = findVerifiedIngredient(ingredient.getIngredientId());

        // 재료 타입이 다를 경우 예외 처리
        if (!ingredient.getClass().equals(findIngredient.getClass())) {
            throw new BusinessLogicException(ExceptionCode.INGREDIENT_TYPE_NOT_MATCH);
        }

        Optional.ofNullable(ingredient.getIngredientName())
                .ifPresent(name -> findIngredient.setIngredientName(name));

        Optional.ofNullable(ingredient.getImage())
                .ifPresent(image -> findIngredient.setImage(image));

        Optional.ofNullable(ingredient.getSubCategory())
                .ifPresent(subCategory -> findIngredient.setSubCategory(subCategory));

        return ingredientRepository.save(findIngredient);
    }

    public void deleteIngredient(long ingredientId) {
        Ingredient ingredient = findVerifiedIngredient(ingredientId);
        ingredientRepository.deleteById(ingredientId);
    }

    private void verifyIngredientExists(String ingredientName) {
        Optional<Ingredient> ingredient = ingredientRepository.findByIngredientName(ingredientName);
        if(ingredient.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.INGREDIENT_EXISTS);
        }
    }

    public Ingredient findVerifiedIngredient(long ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INGREDIENT_NOT_FOUND));
    }
}