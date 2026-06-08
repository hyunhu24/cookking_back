package com.springboot.menu.repository;

import com.springboot.menu.entity.SubMenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubMenuCategoryRepository extends JpaRepository<SubMenuCategory, Long> {
    Optional<SubMenuCategory> findBySubMenuCategoryName(String subMenuCategoryName);
    Optional<SubMenuCategory> findBySubMenuCategoryNameAndMenu_MenuId(String subMenuCategoryName, long menuId);
}