package com.springboot.menu.mapper;

import com.springboot.ingredient.entity.Ingredient;
import com.springboot.ingredient.entity.MainIngredient;
import com.springboot.ingredient.entity.SeasoningIngredient;
import com.springboot.menu.dto.MenuDto;
import com.springboot.menu.dto.MenuIngredientDto;
import com.springboot.menu.entity.Menu;
import com.springboot.menu.entity.MenuIngredient;
import com.springboot.menu.entity.SubMenuCategory;
import com.springboot.menucategory.entity.MenuCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MenuMapper {
//    @Mapping(source = "menuCategory.menuCategoryId", target = "category.menuCategoryId")
//    @Mapping(source = "menuCategory.menuCategoryName", target = "category.menuCategoryName")
//    @Mapping(source = "menuCategory.menuSubCategory", target = "category.menuSubCategory")
//    MenuDto.Response menuToMenuResponseDto(Menu menu);

    default MenuDto.Response menuToMenuResponseDto(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuDto.Response response = new MenuDto.Response();
        response.setMenuId(menu.getMenuId());
        response.setMenuName(menu.getMenuName());

        List<MenuIngredientDto.Response> menuIngredientDtos = menu.getMenuIngredients().stream()
                .map(this::menuIngredientToMenuIngredientResponseDto)
                .collect(Collectors.toList());
        response.setMenuIngredients(menuIngredientDtos);

        MenuCategory menuCategory = menu.getMenuCategory();
        response.setCategory(subMenuCategoriesToMenuCategoryResponseDto(menuCategory, menu));

        return response;
    }

    @Mapping(source = "menuCategoryId", target = "menuCategory.menuCategoryId")
    default Menu menuPostDtoToMenu(MenuDto.Post menuPostDto) {
        if (menuPostDto == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setMenuName(menuPostDto.getMenuName());
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setMenuCategoryId(menuPostDto.getMenuCategoryId());
        menu.setMenuCategory(menuCategory);

        List<MenuIngredient> menuIngredients = menuPostDto.getMenuIngredientDtos().stream()
//            .map(this::menuIngredientPostDtoToMenuIngredient)
                .map(menuIngredientPostDto -> menuIngredientPostDtoToMenuIngredient(menuIngredientPostDto, menu))
                .collect(Collectors.toList());
        menu.setMenuIngredients(menuIngredients);

        return menu;
    }

    @Mapping(source = "menuCategoryId", target = "menuCategory.menuCategoryId")
    Menu menuPatchDtoToMenu(MenuDto.Patch menuPatchDto);
    List<MenuDto.Response> menusToMenuResponseDtos(List<Menu> menus);

    default MenuIngredient menuIngredientPostDtoToMenuIngredient(MenuIngredientDto.Post menuIngredientPostDto, Menu menu) {
        if (menuIngredientPostDto == null) {
            return null;
        }
        MenuIngredient menuIngredient = new MenuIngredient();
        menuIngredient.setMenu(menu);
        Ingredient ingredient;
        if (menuIngredientPostDto.getType().equals("MAIN")) {
            ingredient = new MainIngredient();
        } else {
            ingredient = new SeasoningIngredient();
        }
        ingredient.setIngredientId(menuIngredientPostDto.getIngredientId());

        menuIngredient.setIngredient(ingredient);
        return menuIngredient;
    }

    @Mapping(source = "ingredient.ingredientId", target = "ingredientId")
    @Mapping(source = "ingredient.image", target = "image")
    @Mapping(source = "ingredient.subCategory", target = "subCategory")
    default MenuIngredientDto.Response menuIngredientToMenuIngredientResponseDto(MenuIngredient menuIngredient) {
        if (menuIngredient.getIngredient() == null) {
            return new MenuIngredientDto.Response();
        }
        MenuIngredientDto.Response response = new MenuIngredientDto.Response();
        response.setIngredientId(menuIngredient.getIngredient().getIngredientId());
        response.setIngredientName(menuIngredient.getIngredient().getIngredientName());
        response.setImage(menuIngredient.getIngredient().getImage());
        response.setSubCategory(menuIngredient.getIngredient().getSubCategory());

        String type = menuIngredient instanceof MenuIngredient ?
                "MAIN" :
                "SEASONING";
        response.setType(type);

        return response;
    };

    default MenuDto.MenuCategory subMenuCategoriesToMenuCategoryResponseDto(MenuCategory menuCategory, Menu menu) {

        List<SubMenuCategory> subMenuCategories = menuCategory.getSubMenuCategories();
        MenuDto.MenuCategory response = new MenuDto.MenuCategory();
        SubMenuCategory findSubMenuCategory = subMenuCategories.stream()
                .filter(subMenuCategory ->
                        subMenuCategory.getMenu().stream()
                                .anyMatch(m -> m.getMenuId() == menu.getMenuId())
                )
                .findFirst()
                .orElse(null);
        response.setMenuCategoryId(menuCategory.getMenuCategoryId());
        response.setMenuCategoryName(menuCategory.getMenuCategoryName());
        String subCategoryName = findSubMenuCategory != null ? findSubMenuCategory.getSubMenuCategoryName() : null; // ✅ NPE 방지
        response.setMenuSubCategory(subCategoryName);
        return response;
    }
}