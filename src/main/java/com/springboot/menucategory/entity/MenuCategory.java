package com.springboot.menucategory.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.menu.entity.Menu;
import com.springboot.menu.entity.SubMenuCategory;
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
public class MenuCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long menuCategoryId;

    @Column(nullable = false)
    private String menuCategoryName;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.PERSIST)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.PERSIST)
    private List<SubMenuCategory> subMenuCategories = new ArrayList<>();

    private String menuSubCategory;

    public void setSubMenuCategory(SubMenuCategory subMenuCategory) {
        subMenuCategories.add(subMenuCategory);
        if (subMenuCategory.getMenuCategory() != this) {
            subMenuCategory.setMenuCategory(this);
        }
    }
    public void setMenu(Menu menu) {
        menus.add(menu);
        if (menu.getMenuCategory() != this) {
            menu.setMenuCategory(this);
        }
    }
}