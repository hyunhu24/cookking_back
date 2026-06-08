package com.springboot.menucategory.controller;

import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.menucategory.dto.MenuCategoryDto;
import com.springboot.menucategory.entity.MenuCategory;
import com.springboot.menucategory.mapper.MenuCategoryMapper;
import com.springboot.menucategory.service.MenuCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "메뉴 카테고리 컨트롤러", description = "메뉴 카테고리 관련 API")
@RequestMapping("categories/menus")
@RestController
public class MenuCategoryController {
    private static final String MENU_CATEGORY_URI = "menus/categories/";
    private final MenuCategoryService menuCategoryService;
    private final MenuCategoryMapper mapper;

    public MenuCategoryController(MenuCategoryService menuCategoryService, MenuCategoryMapper mapper) {
        this.menuCategoryService = menuCategoryService;
        this.mapper = mapper;
    }

    @Operation(summary = "메뉴 카테고리 등록", description = "새로운 메뉴 카테고리를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "메뉴 카테고리 등록 완료"),
            @ApiResponse(responseCode = "400", description = "메뉴 카테고리 Validation 실패")
    })
    @PostMapping
    public ResponseEntity postMenuCategories(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                             @RequestBody MenuCategoryDto.Post dto) {
        MenuCategory menuCategory = menuCategoryService.createMenuCategory(mapper.menuCategoryPostDtoToMenuCategory(dto));
        URI location = URI.create(MENU_CATEGORY_URI + menuCategory.getMenuCategoryId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "메뉴 카테고리 수정", description = "기존 메뉴 카테고리 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴 카테고리 수정 완료"),
            @ApiResponse(responseCode = "400", description = "메뉴 카테고리 Validation 실패"),
            @ApiResponse(responseCode = "404", description = "메뉴 카테고리를 찾을 수 없음")
    })
    @PatchMapping("/{menucategory-id}")
    public ResponseEntity patchMenuCategories(@PathVariable("menucategory-id") long menuCategoryId,
                                              @RequestBody MenuCategoryDto.Patch dto) {
        dto.setMenuCategoryId(menuCategoryId);
        MenuCategory menuCategory = mapper.menuCategoryPatchDtoToMenuCategory(dto);
        menuCategory.setMenuCategoryId(menuCategoryId);

        MenuCategory updateMenuCategory = menuCategoryService.updateMenuCategory(menuCategory);
        MenuCategoryDto.Response response = mapper.menuCategoryToMenuCategoryResponseDto(updateMenuCategory);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Operation(summary = "메뉴 카테고리 단일 조회", description = "특정 메뉴 카테고리를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴 카테고리 조회 완료"),
            @ApiResponse(responseCode = "404", description = "메뉴 카테고리를 찾을 수 없음")
    })
    @GetMapping("/{menu-id}")
    public ResponseEntity getMenuCategory(@PathVariable("menu-id") long menuId) {
        MenuCategory menuCategory = menuCategoryService.findMenuCategory(menuId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.menuCategoryToMenuCategoryResponseDto(menuCategory)),
                HttpStatus.OK);
    }

    @Operation(summary = "메뉴 카테고리 전체 조회", description = "모든 메뉴 카테고리를 페이지네이션과 함께 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴 카테고리 전체 조회 완료"),
            @ApiResponse(responseCode = "400", description = "요청 파라미터 Validation 실패")
    })
    @GetMapping
    public ResponseEntity getMenuCategories(
            @Parameter(hidden = true) @AuthenticationPrincipal Member member, @RequestParam int page, @RequestParam int size) {
        Page<MenuCategory> pageMenuCategories = menuCategoryService.findMenuCategories(page - 1, size);
        List<MenuCategory> menuCategories = pageMenuCategories.getContent();
        List<MenuCategoryDto.Response> response = mapper.menuCategoriesToMenuCategoriesResponseDtos(menuCategories);
        return new ResponseEntity<>(new MultiResponseDto<>(response, pageMenuCategories), HttpStatus.OK);

    }

    @Operation(summary = "메뉴 카테고리 삭제", description = "특정 메뉴 카테고리를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "메뉴 카테고리 삭제 완료"),
            @ApiResponse(responseCode = "404", description = "메뉴 카테고리를 찾을 수 없음")
    })
    @DeleteMapping
    public ResponseEntity deleteMenuCategory(@RequestParam long menuCategoryId) {
        menuCategoryService.deleteMenuCategory(menuCategoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}