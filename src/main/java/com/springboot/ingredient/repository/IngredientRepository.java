package com.springboot.ingredient.repository;

import com.springboot.ingredient.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i FROM Ingredient i WHERE TYPE(i) = MainIngredient")
    Page<Ingredient> findMainIngredients(Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE TYPE(i) = SeasoningIngredient")
    Page<Ingredient> findSeasoningIngredients(Pageable pageable);

    Optional<Ingredient> findByIngredientName(String ingredientName);
}