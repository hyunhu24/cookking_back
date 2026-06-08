package com.springboot.menu.entity;

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
public class MenuIngredient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long menuIngredientId;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public void setMenu(Menu menu) {
        this.menu = menu;
        if (menu.getMenuIngredients().contains(this)) {
            menu.setMenuIngredient(this);
        }
    }
}