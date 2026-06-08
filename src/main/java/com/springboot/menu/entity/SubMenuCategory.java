package com.springboot.menu.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.menucategory.entity.MenuCategory;
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
public class SubMenuCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long subMenuCategoryId;

    @Column(nullable = false)
    private String subMenuCategoryName;

    @ManyToOne
    @JoinColumn(name = "menu_category_id")
    private MenuCategory menuCategory;

    @OneToMany(mappedBy = "subMenuCategory", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Menu> menu = new ArrayList<>();

    public void setMenu(Menu menu) {
        this.menu.add(menu);
        if (menu.getSubMenuCategory() != this) {
            menu.setSubMenuCategory(this);
        }
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
        if (!menuCategory.getSubMenuCategories().contains(this)) {
            menuCategory.setSubMenuCategory(this);
        }
    }
}