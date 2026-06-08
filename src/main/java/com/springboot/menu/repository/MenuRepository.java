package com.springboot.menu.repository;

import com.springboot.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByMenuName(String menuName);
    Optional<Menu> findByMenuNameAndMenuCategory_MenuCategoryId(String menuName, Long menuCategoryId);
    Optional<Menu> findByMenuNameAndMenuCategory_MenuCategoryIdAndMenuCategory_MenuSubCategory(String menuName, Long menuCategoryId, String menuSubCategory);
    // MenuName + MenuCategoryId + SubMenuCategoryId 기준으로 찾기
    Optional<Menu> findByMenuNameAndMenuCategory_MenuCategoryIdAndSubMenuCategory_SubMenuCategoryId(
            String menuName, Long menuCategoryId, Long subMenuCategoryId
    );

}