package com.springboot.menu.controller;

import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.ingredient.mapper.IngredientMapper;
import com.springboot.member.entity.Member;
import com.springboot.menu.dto.MenuDto;
import com.springboot.menu.entity.Menu;
import com.springboot.menu.mapper.MenuMapper;
import com.springboot.menu.service.MenuService;
import com.springboot.recipeboard.dto.RecipeBoardDto;
import com.springboot.recipeboard.entity.RecipeBoard;
import com.springboot.recipeboard.mapper.RecipeBoardMapper;
import com.springboot.recipeboard.repository.RecipeBoardRepository;
import com.springboot.recipeboard.service.RecipeBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Tag(name = "메뉴 컨트롤러", description = "메뉴 관련 컨트롤러")
@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;
    private final MenuMapper menuMapper;
    private final IngredientMapper ingredientMapper;
    private final RecipeBoardMapper recipeBoardMapper;
    private final RecipeBoardService recipeBoardService;
    private final RecipeBoardRepository recipeBoardRepository;
    private static final String MENU_URI = "menus/";

    public MenuController(MenuService menuService, MenuMapper menuMapper, IngredientMapper ingredientMapper, RecipeBoardMapper recipeBoardMapper, RecipeBoardService recipeBoardService, RecipeBoardRepository recipeBoardRepository) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
        this.ingredientMapper = ingredientMapper;
        this.recipeBoardMapper = recipeBoardMapper;
        this.recipeBoardService = recipeBoardService;
        this.recipeBoardRepository = recipeBoardRepository;
    }

//    @Operation(summary = "메뉴 추천 및 재추천", description = "사용자가 선택한 재료를 바탕으로 카테고리별 메뉴를 추천합니다. 매번 랜덤하게 레시피를 추천합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "추천된 메뉴 조회 완료"),
//            @ApiResponse(responseCode = "400", description = "잘못된 재료 선택 (주재료 2개 이상 필수)")
//    })
//    @PostMapping("/recommendations")
//    public ResponseEntity recommendMenus( // RequestBody 담아야됨
//                                          @Parameter(hidden = true) @AuthenticationPrincipal Member member,
//                                          @RequestBody MenuDto.RecommendationsIngredientDto IngredientDto,
//                                          @Parameter(description = "페이지, 기본값은 1") @RequestParam(value = "page", defaultValue = "1") int page,
//                                          @Parameter(description = "사이즈, 기본값은 10") @RequestParam(value = "size", defaultValue = "10") int size) {
//
//        List<RecipeBoard> recipeBoard = menuService.recommendMenus(ingredientMapper.ingredientsDtoToIngredients(IngredientDto), page - 1, size);
////        List<RecipeBoard> recipeBoardList = recipeBoard.getContent();
//        Page<RecipeBoard> pageImpe = new PageImpl<>(
//                recipeBoard,                      // content
//                PageRequest.of(page - 1, size),     // pageable (현재 page-1 주는 거 맞게)
//                recipeBoard.size()                 // totalElements (전체 개수 그냥 리스트 사이즈로)
//        );
//
//        return new ResponseEntity(
//                new MultiResponseDto<>(recipeBoardMapper.recipeBoardsToRecipeBoardResponseDtos(recipeBoard),
//                        pageImpe), HttpStatus.OK);
//    }

    @Operation(summary = "메뉴 추천 및 재추천", description = "사용자가 선택한 재료를 바탕으로 카테고리별 메뉴를 추천합니다. 매번 랜덤하게 레시피를 추천합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴 이름 추천 성공"),
            @ApiResponse(responseCode = "400", description = "주재료 부족 또는 예외 발생")
    })
    @PostMapping("/recommendations")
    public ResponseEntity recommendMenuName(@RequestBody MenuDto.RecommendationsIngredientDto ingredientDto) {
        Menu recommendedMenu = menuService.recommendRandomMenu(ingredientMapper.ingredientsDtoToIngredients(ingredientDto));

        // 해당 메뉴에 연결된 모든 RecipeBoard 가져오기
        List<RecipeBoard> recipeBoards = new ArrayList<>(
                recipeBoardRepository.findByMenu_MenuId(recommendedMenu.getMenuId(), Pageable.unpaged()).getContent()
        );

        if (recipeBoards.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.RECIPEBOARD_NOT_FOUND);
        }

        // 대표 이미지 랜덤 추출
        Collections.shuffle(recipeBoards);
        String representativeImage = recipeBoards.get(0).getImage();

        // 공백 제거해서 보냄
        MenuDto.RecommendationResponse response = new MenuDto.RecommendationResponse(
                recommendedMenu.getMenuId(),
                recommendedMenu.getMenuName().replaceAll("\\s+", ""),
                representativeImage
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "메뉴 등록", description = "새로운 메뉴를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "메뉴 등록 완료"),
            @ApiResponse(responseCode = "400", description = "메뉴 Validation 실패")
    })
    @PostMapping
    public ResponseEntity postMenu(@RequestBody MenuDto.Post menuPostDto,
                                   @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        // 메뉴 등록 로직
        Menu menu = menuService.createMenu(menuMapper.menuPostDtoToMenu(menuPostDto));
        URI location = URI.create(MENU_URI + menu.getMenuId());

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "메뉴 수정", description = "기존 메뉴 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴 수정 완료"),
            @ApiResponse(responseCode = "400", description = "메뉴 Validation 실패")
    })
    @PatchMapping
    public ResponseEntity patchMenu(@RequestBody MenuDto.Patch menuPatchDto,
                                    @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        // 메뉴 수정 로직
        Menu menu = menuService.updateMenu(menuMapper.menuPatchDtoToMenu(menuPatchDto));
        return new ResponseEntity<>(new SingleResponseDto<>(menuMapper.menuToMenuResponseDto(menu)), HttpStatus.OK);
    }

    @Operation(summary = "메뉴 단일 조회", description = "특정 메뉴의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴 조회 완료"),
            @ApiResponse(responseCode = "404", description = "메뉴를 찾을 수 없음")
    })
    @GetMapping("/{menu-id}")
    public ResponseEntity getMenu(@PathVariable("menu-id") long menuId,
                                  @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        // 메뉴 조회 로직
        Menu menu = menuService.findMenu(menuId);
        return new ResponseEntity<>(new SingleResponseDto<>(menuMapper.menuToMenuResponseDto(menu)), HttpStatus.OK);
    }

    @Operation(summary = "메뉴 전체 조회", description = "모든 메뉴를 페이지네이션과 함께 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴 전체 조회 완료"),
            @ApiResponse(responseCode = "400", description = "요청 파라미터 Validation 실패")
    })
    @GetMapping
    public ResponseEntity getMenus(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        // 메뉴 목록 조회 로직
        Page<Menu> pageMenus = menuService.findMenus(page - 1, size);
        // Page<Menu> -> List<MenuDto.SimpleResponse>
        List<Menu> menus = pageMenus.getContent();
        // Page<Menu> -> List<MenuDto.SimpleResponse>
        List<MenuDto.Response> menuSimpleResponses = menuMapper.menusToMenuResponseDtos(menus);
        return new ResponseEntity<>(new MultiResponseDto<>(menuSimpleResponses, pageMenus), HttpStatus.OK);

    }

    @Operation(summary = "메뉴 이름으로 레시피 게시글 조회", description = "특정 메뉴 이름에 해당하는 레시피 게시글들을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "레시피 게시글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 메뉴 이름의 레시피 게시글이 없습니다.")
    })
    @GetMapping("/recipes")
    public ResponseEntity getRecipeBoardsByMenuName(@RequestParam("name") String menuName,
                                                    @RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @Parameter(hidden = true) @AuthenticationPrincipal Member member) {

        Page<RecipeBoard> recipeBoards = recipeBoardService.findRecipeBoardsByMenuName(menuName, page - 1, size);
        List<RecipeBoardDto.Response> responses = recipeBoardMapper.recipeBoardsToRecipeBoardResponseDtos(recipeBoards.getContent(), member.getMemberId());

        return new ResponseEntity<>(new MultiResponseDto<>(responses, recipeBoards), HttpStatus.OK);
    }

}