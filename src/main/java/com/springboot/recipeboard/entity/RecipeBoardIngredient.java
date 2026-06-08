package com.springboot.recipeboard.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.ingredient.entity.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RecipeBoardIngredient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeBoardIngredientId;

    @ManyToOne
    @JoinColumn(name = "recipe_board_id")
    private RecipeBoard recipeBoard;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public void setRecipeBoard(RecipeBoard recipeBoard) {
        this.recipeBoard = recipeBoard;
        if (!recipeBoard.getRecipeBoardIngredients().contains(this)) {
            recipeBoard.setRecipeBoardIngredient(this);
        }
    }
}