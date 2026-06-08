package com.springboot.menucategory.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.menucategory.dto.MenuCategoryDto;
import com.springboot.menucategory.entity.MenuCategory;
import com.springboot.menucategory.repository.MenuCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuCategoryService {
    private final MenuCategoryRepository menuCategoryRepository;

    public MenuCategoryService(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    public MenuCategory createMenuCategory(MenuCategory menuCategory) {
        // 중복 카테고리명 체크
        existMenuCategoryName(menuCategory.getMenuCategoryName());

        return menuCategoryRepository.save(menuCategory);
    }

    public MenuCategory updateMenuCategory(MenuCategory menuCategory) {
        MenuCategory findMenuCategory = verifyMenuCategoryId(menuCategory.getMenuCategoryId());

        Optional.ofNullable(menuCategory.getMenuCategoryName()).ifPresent(findMenuCategory::setMenuCategoryName);

        return menuCategoryRepository.save(findMenuCategory);
    }

    public MenuCategory findMenuCategory(long menuCategoryId) {
        return verifyMenuCategoryId(menuCategoryId);
    }

    public Page<MenuCategory> findMenuCategories(int page, int size) {
        return menuCategoryRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteMenuCategory(Long menuCategoryId) {
        MenuCategory findMenuCategory = verifyMenuCategoryId(menuCategoryId);
        menuCategoryRepository.delete(findMenuCategory);
    }

    private void existMenuCategoryName(String menuCategoryName) {
        menuCategoryRepository.findByMenuCategoryName(menuCategoryName)
                .ifPresent(m -> {
                    throw new BusinessLogicException(ExceptionCode.MENU_CATEGORY_EXISTS);
                });
    }

    private MenuCategory verifyMenuCategoryId(long menuCategoryId) {
        return menuCategoryRepository.findByMenuCategoryId(menuCategoryId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MENU_CATEGORY_NOT_FOUND));
    }
}