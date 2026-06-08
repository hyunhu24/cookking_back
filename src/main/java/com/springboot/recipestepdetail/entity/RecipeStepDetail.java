package com.springboot.recipestepdetail.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.recipeboard.entity.RecipeBoard;
import com.springboot.recipeboard.entity.RecipeBoardStep;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RecipeStepDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeStepDetailId;

    @Column(nullable = false)
    private int detailOrderNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "recipe_board_step_id")
    private RecipeBoardStep recipeBoardStep;

    public void setRecipeBoardStep(RecipeBoardStep recipeBoardStep) {
        this.recipeBoardStep = recipeBoardStep;
        if (!recipeBoardStep.getRecipeStepDetails().contains(this)) {
            recipeBoardStep.getRecipeStepDetails().add(this);
        }
    }
}