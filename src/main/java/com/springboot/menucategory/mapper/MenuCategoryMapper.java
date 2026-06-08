package com.springboot.menucategory.mapper;

import com.springboot.menu.dto.MenuDto;
import com.springboot.menu.entity.Menu;
import com.springboot.menu.entity.SubMenuCategory;
import com.springboot.menucategory.dto.MenuCategoryDto;
import com.springboot.menucategory.entity.MenuCategory;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MenuCategoryMapper {
    // DTO to Entity
    MenuCategory menuCategoryPostDtoToMenuCategory(MenuCategoryDto.Post post);
    MenuCategory menuCategoryPatchDtoToMenuCategory(MenuCategoryDto.Patch patch);

    // Entity to DTO
    default MenuCategoryDto.Response menuCategoryToMenuCategoryResponseDto(MenuCategory menuCategory) {
        if (menuCategory == null) {
            return null;
        }

        MenuCategoryDto.Response response = new MenuCategoryDto.Response();
        response.setMenuCategoryId(menuCategory.getMenuCategoryId());
        response.setMenuCategoryName(menuCategory.getMenuCategoryName());
        response.setMenus(menuCategory.getMenus().stream()
                .map(menu -> new MenuDto.SimpleResponse(
                        menu.getMenuId(),
                        menu.getMenuName(),
                        subMenuCategoriesToMenuCategoryResponseDto(menuCategory, menu).getMenuCategoryName()
                ))
                .collect(Collectors.toList()));
        return response;
    };

    List<MenuCategoryDto.Response> menuCategoriesToMenuCategoriesResponseDtos(List<MenuCategory> menuCategories);

    default MenuDto.MenuCategory subMenuCategoriesToMenuCategoryResponseDto(MenuCategory menuCategory, Menu menu) {

        List<SubMenuCategory> subMenuCategories = menuCategory.getSubMenuCategories();
        MenuDto.MenuCategory response = new MenuDto.MenuCategory();
//        SubMenuCategory findSubMenuCategory = subMenuCategories.stream()
//                .filter(subMenuCategory -> subMenuCategory.getMenu().getMenuId() == menu.getMenuId())
//                .findFirst().orElse(null);
        SubMenuCategory findSubMenuCategory = subMenuCategories.stream()
                .filter(subMenuCategory ->
                        subMenuCategory.getMenu().stream()
                                .anyMatch(m -> m.getMenuId() == menu.getMenuId())
                )
                .findFirst()
                .orElse(null);
        response.setMenuCategoryId(menuCategory.getMenuCategoryId());
        response.setMenuCategoryName(menuCategory.getMenuCategoryName());
        String subCategoryName = findSubMenuCategory != null ? findSubMenuCategory.getSubMenuCategoryName() : null;
        response.setMenuSubCategory(subCategoryName);
        return response;
    }

}