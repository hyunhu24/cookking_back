package com.springboot.ingredient.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MAIN")
public class MainIngredient extends Ingredient {
    private String subIngredient;
}
