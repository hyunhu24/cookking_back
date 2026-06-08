package com.springboot.menucategory.repository;

import com.springboot.menu.entity.Menu;
import com.springboot.menucategory.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    Optional<MenuCategory> findByMenuCategoryName(String menuCategoryName);
    Optional<MenuCategory> findByMenuCategoryId(long menuCategoryId);
}