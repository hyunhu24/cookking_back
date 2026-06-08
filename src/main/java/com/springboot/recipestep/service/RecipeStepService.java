package com.springboot.recipestep.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.recipestep.entity.RecipeStep;
import com.springboot.recipestep.repository.RecipeStepRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeStepService {
    private final RecipeStepRepository recipeStepRepository;

    public RecipeStepService(RecipeStepRepository recipeStepRepository) {
        this.recipeStepRepository = recipeStepRepository;
    }

    // Add methods to handle business logic related to RecipeStep
    public RecipeStep createRecipeStep(RecipeStep recipeStep) {
        verifyRecipeStepExists(recipeStep.getTitle());

        return recipeStepRepository.save(recipeStep);
    }

    public RecipeStep findRecipeStep(long recipeStepId) {
        return findVerifiedRecipeStep(recipeStepId);
    }

    public Page<RecipeStep> findRecipeSteps(int page, int size) {
        return recipeStepRepository.findAll(PageRequest.of(page, size, Sort.by("recipeStepId").ascending()));
    }

    public RecipeStep updateRecipeStep(RecipeStep recipeStep) {
        RecipeStep findRecipeStep = findVerifiedRecipeStep(recipeStep.getRecipeStepId());

        Optional.ofNullable(recipeStep.getTitle())
                .ifPresent(title -> findRecipeStep.setTitle(title));

        return recipeStepRepository.save(findRecipeStep);
    }

    public void deleteRecipeStep(long recipeStepId) {
        RecipeStep recipeStep = findVerifiedRecipeStep(recipeStepId);
        recipeStepRepository.deleteById(recipeStepId);
    }

    private void verifyRecipeStepExists(String title) {
        Optional<RecipeStep> recipeStep = recipeStepRepository.findByTitle(title);
        if (recipeStep.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.RECIPE_STEP_EXISTS);
        }
    }

    public RecipeStep findVerifiedRecipeStep(long recipeStepId) {
        return recipeStepRepository.findById(recipeStepId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.RECIPE_STEP_NOT_FOUND));
    }
}