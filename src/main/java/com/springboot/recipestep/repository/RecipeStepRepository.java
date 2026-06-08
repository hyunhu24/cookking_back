package com.springboot.recipestep.repository;

import com.springboot.recipestep.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
    Optional<RecipeStep> findByRecipeStepId(Long stepId);
    Optional<RecipeStep> findByTitle(String title);
}