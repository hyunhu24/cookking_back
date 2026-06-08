package com.springboot.recipeboard.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.recipestep.entity.RecipeStep;
import com.springboot.recipestepdetail.entity.RecipeStepDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RecipeBoardStep extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeBoardStepId;

    @Column(nullable = false)
    private int stepOrder;

    @ManyToOne
    @JoinColumn(name = "recipe_board_id")
    private RecipeBoard recipeBoard;

    @ManyToOne
    @JoinColumn(name = "recipe_step_id")
    private RecipeStep recipeStep;

    // cascade 수정
    @OneToMany(mappedBy = "recipeBoardStep", cascade = {CascadeType.ALL})
    private List<RecipeStepDetail> recipeStepDetails = new ArrayList<>();

    public void setRecipeStepDetails(List<RecipeStepDetail> recipeStepDetails) {
        this.recipeStepDetails = recipeStepDetails;
        for (RecipeStepDetail recipeStepDetail : recipeStepDetails) {
            recipeStepDetail.setRecipeBoardStep(this);
        }
    }

    public void setRecipeBoard(RecipeBoard recipeBoard) {
        this.recipeBoard = recipeBoard;
        if (!recipeBoard.getRecipeBoardSteps().contains(this)) {
            recipeBoard.getRecipeBoardSteps().add(this);
        }
    }
}