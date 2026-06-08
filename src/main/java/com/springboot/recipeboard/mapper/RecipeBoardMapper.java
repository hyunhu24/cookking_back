package com.springboot.recipeboard.mapper;

import com.springboot.ingredient.dto.IngredientDto;
import com.springboot.ingredient.entity.Ingredient;
import com.springboot.ingredient.entity.MainIngredient;
import com.springboot.ingredient.entity.SeasoningIngredient;
import com.springboot.menu.dto.MenuDto;
import com.springboot.menu.entity.Menu;
import com.springboot.menu.entity.SubMenuCategory;
import com.springboot.menucategory.entity.MenuCategory;
import com.springboot.recipeboard.dto.RecipeBoardDto;
import com.springboot.recipeboard.dto.RecipeBoardStepDto;
import com.springboot.recipeboard.entity.RecipeBoard;
import com.springboot.recipeboard.entity.RecipeBoardIngredient;
import com.springboot.recipeboard.entity.RecipeBoardStep;
import com.springboot.recipestep.entity.RecipeStep;
import com.springboot.recipestepdetail.dto.RecipeStepDetailDto;
import com.springboot.recipestepdetail.entity.RecipeStepDetail;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RecipeBoardMapper {
    default RecipeBoard recipeBoardPatchDtoToRecipeBoard(RecipeBoardDto.Patch patchDto) {
        if (patchDto == null) return null;

        RecipeBoard recipeBoard = new RecipeBoard();
        recipeBoard.setRecipeBoardId(patchDto.getRecipeBoardId());
        recipeBoard.setTitle(patchDto.getTitle());
        recipeBoard.setImage(patchDto.getImage());
        recipeBoard.setRecipeStatus(patchDto.getRecipeStatus());

        // 메뉴 설정은 안됨. 못바꿈 돌아가
//        Menu menu = new Menu();
//        menu.setMenuId(patchDto.getMenuId());
//        recipeBoard.setMenu(menu);

        // 단계 리스트 매핑
        // null일경우 바꾸지 않는것으로, null로 할당
        List<RecipeBoardStep> boardSteps = patchDto.getRecipeBoardSteps() != null ? patchDto.getRecipeBoardSteps().stream()
                .map(stepDto -> recipeBoardStepPostDtoToEntity(stepDto, recipeBoard))
                .collect(Collectors.toList()) : null;
        recipeBoard.setRecipeBoardSteps(boardSteps);

        // 재료 리스트 매핑
        // null일경우 바꾸지 않는것으로, null로 할당
        // 재료 리스트 매핑
        List<RecipeBoardIngredient> recipeBoardIngredients = patchDto.getIngredients() != null ? patchDto.getIngredients().stream()
                .map(ingredientDto -> RecipeBoardIngredientDtoToEntity(ingredientDto, recipeBoard))
                .collect(Collectors.toList()) : null;
        recipeBoard.setRecipeBoardIngredients(recipeBoardIngredients);

        return recipeBoard;
    }

    default RecipeBoard recipeBoardPostDtoToRecipeBoard(RecipeBoardDto.Post postDto, Long memberId) {
        if (postDto == null) return null;

        // 메뉴도 선택해야 함.
        RecipeBoard recipeBoard = new RecipeBoard();
        recipeBoard.setTitle(postDto.getTitle());
        recipeBoard.setImage(postDto.getImage());
        recipeBoard.setRecipeStatus(postDto.getRecipeStatus());

        // 메뉴 설정
        Menu menu = new Menu();
        menu.setMenuName(postDto.getMenuName());
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setMenuCategoryId(postDto.getMenuCategoryId());
        menuCategory.setMenuSubCategory(postDto.getMenuSubCategory());
        menu.setMenuCategory(menuCategory);
        menuCategory.setMenu(menu);
        recipeBoard.setMenu(menu);

        // 작성자 설정
        Member member = new Member();
        member.setMemberId(memberId);
        recipeBoard.setMember(member);

        // 단계 리스트 매핑
        List<RecipeBoardStep> boardSteps = postDto.getRecipeBoardSteps().stream()
                .map(stepDto -> recipeBoardStepPostDtoToEntity(stepDto, recipeBoard))
                .collect(Collectors.toList());

        recipeBoard.setRecipeBoardSteps(boardSteps);

        // 재료 리스트 매핑
        List<RecipeBoardIngredient> recipeBoardIngredients = postDto.getIngredients().stream()
                .map(ingredientDto -> RecipeBoardIngredientDtoToEntity(ingredientDto, recipeBoard))
                .collect(Collectors.toList());

        return recipeBoard;
    }

    // 🔹 개별 RecipeBoardIngredient 매핑 (부모 RecipeBoard 주입)
    default RecipeBoardIngredient RecipeBoardIngredientDtoToEntity(IngredientDto.IdRequest dto, RecipeBoard parentBoard) {
        RecipeBoardIngredient recipeBoardIngredient = new RecipeBoardIngredient();

        // ingredient (마스터) ID 참조만
        Ingredient ingredient = dto.getType().equals("SEASONING") ?
                new SeasoningIngredient() :
                new MainIngredient();

        ingredient.setIngredientId(dto.getIngredientId());
        recipeBoardIngredient.setIngredient(ingredient);

        // 양방향 설정
        recipeBoardIngredient.setRecipeBoard(parentBoard);

        return recipeBoardIngredient;
    }

    // 🔹 개별 RecipeBoardStep 매핑 (부모 RecipeBoard 주입)
    default RecipeBoardStep recipeBoardStepPostDtoToEntity(RecipeBoardStepDto.Post dto, RecipeBoard parentBoard) {
        RecipeBoardStep step = new RecipeBoardStep();
        step.setStepOrder(dto.getStepOrder());

        // recipeStep (마스터) ID 참조만
        RecipeStep recipeStep = new RecipeStep();
        recipeStep.setRecipeStepId(dto.getRecipeStepId());
        step.setRecipeStep(recipeStep);

        // 양방향 설정
        step.setRecipeBoard(parentBoard);

        // stepDetail 매핑 및 양방향 설정
        List<RecipeStepDetail> details = dto.getRecipeStepDetails().stream()
                .map(this::recipeStepDetailPostDtoToRecipeStepDetail)
                .collect(Collectors.toList());

        step.setRecipeStepDetails(details); // 내부에서 양방향 설정됨

        return step;
    }

    // 🔹 RecipeStepDetailMapper 메서드 재사용
    default RecipeStepDetail recipeStepDetailPostDtoToRecipeStepDetail(RecipeStepDetailDto.Post dto) {
        RecipeStepDetail stepDetail = new RecipeStepDetail();
        stepDetail.setDetailOrderNumber(dto.getStepOrder());
        stepDetail.setDescription(dto.getDescription());
        stepDetail.setImage(dto.getImage());
        return stepDetail;
    }

    // 아래는 수정해야 하는 메서드
    default RecipeBoardDto.Response recipeBoardToRecipeBoardResponseDto(RecipeBoard recipeBoard, long memberId) {
        if (recipeBoard == null) return null;

        RecipeBoardDto.Response response = new RecipeBoardDto.Response();
        response.setRecipeBoardId(recipeBoard.getRecipeBoardId());
        response.setMemberId(recipeBoard.getMember().getMemberId());
        response.setTitle(recipeBoard.getTitle());
        response.setImage(recipeBoard.getImage());
        response.setMenuId(recipeBoard.getMenu().getMenuId());
        response.setRecipeStatus(recipeBoard.getRecipeStatus());
        response.setRecipeBoardStatus(recipeBoard.getRecipeBoardStatus());
        response.setNickName(recipeBoard.getMember().getNickName());
        response.setCreatedAt(recipeBoard.getCreatedAt());

        List<RecipeBoardDto.RecipeStep> step = recipeBoard.getRecipeBoardSteps().stream()
                .map(recipeBoardStep -> {
                    RecipeBoardDto.RecipeStep recipeStep = new RecipeBoardDto.RecipeStep();
                    recipeStep.setRecipeStepId(recipeBoardStep.getRecipeStep().getRecipeStepId());
                    recipeStep.setStepOrder(recipeBoardStep.getStepOrder());
                    recipeStep.setTitle(recipeBoardStep.getRecipeStep().getTitle());
                    recipeStep.setStepOrder(recipeBoardStep.getStepOrder());
                    List<RecipeStepDetailDto.Response> stepDetails = recipeBoardStep.getRecipeStepDetails().stream()
                            .map(this::recipeStepDetailToRecipeStepDetailResponseDto)
                            .collect(Collectors.toList());
                    recipeStep.setRecipeBoardSteps(stepDetails);
                    return recipeStep;
                }).collect(Collectors.toList());

        response.setRecipeStep(step);

        List<IngredientDto.Response> mainIngredients = recipeBoard.getRecipeBoardIngredients().stream()
                .filter(recipeBoardIngredient -> recipeBoardIngredient.getIngredient() instanceof MainIngredient)
                .map(recipeBoardIngredient -> {
                    IngredientDto.Response recipeIngredient = new IngredientDto.Response();
                    recipeIngredient.setIngredientId(recipeBoardIngredient.getIngredient().getIngredientId());
                    recipeIngredient.setIngredientName(recipeBoardIngredient.getIngredient().getIngredientName());
                    recipeIngredient.setImage(recipeBoardIngredient.getIngredient().getImage());
                    recipeIngredient.setSubCategory(recipeBoardIngredient.getIngredient().getSubCategory());
                    return recipeIngredient;
                }).collect(Collectors.toList());
        response.setMainIngredients(mainIngredients);

        List<IngredientDto.Response> seasoningIngredients = recipeBoard.getRecipeBoardIngredients().stream()
                .filter(recipeBoardIngredient -> recipeBoardIngredient.getIngredient() instanceof SeasoningIngredient)
                .map(recipeBoardIngredient -> {
                    IngredientDto.Response recipeIngredient = new IngredientDto.Response();
                    recipeIngredient.setIngredientId(recipeBoardIngredient.getIngredient().getIngredientId());
                    recipeIngredient.setIngredientName(recipeBoardIngredient.getIngredient().getIngredientName());
                    recipeIngredient.setImage(recipeBoardIngredient.getIngredient().getImage());
                    recipeIngredient.setSubCategory(null);
                    return recipeIngredient;
                }).collect(Collectors.toList());

        response.setSeasoningIngredients(seasoningIngredients);

        // 좋아요 수
        response.setLikeCount(recipeBoard.getLike().size());
        // 좋아요 여부
        response.setLiked(recipeBoard.getLike().stream()
                .anyMatch(like -> like.getMember().getMemberId() == memberId));
        // 북마크 여부
        response.setBookmarked(recipeBoard.getBookmarks().stream()
                .anyMatch(bookmark -> bookmark.getMember().getMemberId() == memberId));

        return response;
    }

    default RecipeBoardDto.Response recipeBoardToRecipeBoardResponseDto(RecipeBoard recipeBoard) {
        if (recipeBoard == null) return null;

        RecipeBoardDto.Response response = new RecipeBoardDto.Response();
        response.setRecipeBoardId(recipeBoard.getRecipeBoardId());
        response.setTitle(recipeBoard.getTitle());
        response.setImage(recipeBoard.getImage());
        response.setMenuId(recipeBoard.getMenu().getMenuId());
        response.setRecipeStatus(recipeBoard.getRecipeStatus());
        response.setRecipeBoardStatus(recipeBoard.getRecipeBoardStatus());
        response.setNickName(recipeBoard.getMember().getNickName());
        response.setCreatedAt(recipeBoard.getCreatedAt());

        List<RecipeBoardDto.RecipeStep> step = recipeBoard.getRecipeBoardSteps().stream()
                .map(recipeBoardStep -> {
                    RecipeBoardDto.RecipeStep recipeStep = new RecipeBoardDto.RecipeStep();
                    recipeStep.setRecipeStepId(recipeBoardStep.getRecipeStep().getRecipeStepId());
                    recipeStep.setStepOrder(recipeBoardStep.getStepOrder());
                    recipeStep.setTitle(recipeBoardStep.getRecipeStep().getTitle());
                    recipeStep.setStepOrder(recipeBoardStep.getStepOrder());
                    List<RecipeStepDetailDto.Response> stepDetails = recipeBoardStep.getRecipeStepDetails().stream()
                            .map(this::recipeStepDetailToRecipeStepDetailResponseDto)
                            .collect(Collectors.toList());
                    recipeStep.setRecipeBoardSteps(stepDetails);
                    return recipeStep;
                }).collect(Collectors.toList());

        response.setRecipeStep(step);

        List<IngredientDto.Response> mainIngredients = recipeBoard.getRecipeBoardIngredients().stream()
                .filter(recipeBoardIngredient -> recipeBoardIngredient.getIngredient() instanceof MainIngredient)
                .map(recipeBoardIngredient -> {
                    IngredientDto.Response recipeIngredient = new IngredientDto.Response();
                    recipeIngredient.setIngredientId(recipeBoardIngredient.getIngredient().getIngredientId());
                    recipeIngredient.setIngredientName(recipeBoardIngredient.getIngredient().getIngredientName());
                    recipeIngredient.setImage(recipeBoardIngredient.getIngredient().getImage());
                    recipeIngredient.setSubCategory(recipeBoardIngredient.getIngredient().getSubCategory());
                    return recipeIngredient;
                }).collect(Collectors.toList());
        response.setMainIngredients(mainIngredients);

        List<IngredientDto.Response> seasoningIngredients = recipeBoard.getRecipeBoardIngredients().stream()
                .filter(recipeBoardIngredient -> recipeBoardIngredient.getIngredient() instanceof SeasoningIngredient)
                .map(recipeBoardIngredient -> {
                    IngredientDto.Response recipeIngredient = new IngredientDto.Response();
                    recipeIngredient.setIngredientId(recipeBoardIngredient.getIngredient().getIngredientId());
                    recipeIngredient.setIngredientName(recipeBoardIngredient.getIngredient().getIngredientName());
                    recipeIngredient.setImage(recipeBoardIngredient.getIngredient().getImage());
                    recipeIngredient.setSubCategory(null);
                    return recipeIngredient;
                }).collect(Collectors.toList());

        response.setSeasoningIngredients(seasoningIngredients);

        // 좋아요 수
        response.setLikeCount(recipeBoard.getLike().size());
        // 좋아요 여부
        response.setLiked(false);
        // 북마크 여부
        response.setBookmarked(false);

        return response;
    }

    default RecipeStepDetailDto.Response recipeStepDetailToRecipeStepDetailResponseDto(RecipeStepDetail stepDetail) {
        if (stepDetail == null) return null;

        RecipeStepDetailDto.Response response = new RecipeStepDetailDto.Response();
        response.setRecipeStepDetailId(stepDetail.getRecipeStepDetailId());
        response.setStepOrder(stepDetail.getDetailOrderNumber());
        response.setDescription(stepDetail.getDescription());
        response.setImage(stepDetail.getImage());

        return response;
    }

    default List<RecipeBoardDto.Response> recipeBoardsToRecipeBoardResponseDtos(List<RecipeBoard> recipeBoards, long memberId) {
        if (recipeBoards == null) return null;

        List<RecipeBoardDto.Response> list = new ArrayList<>(recipeBoards.size());
        for (RecipeBoard recipeBoard : recipeBoards) {
            list.add(recipeBoardToRecipeBoardResponseDto(recipeBoard, memberId));
        }

        return list;
    }

    default List<RecipeBoardDto.Response> recipeBoardsToRecipeBoardResponseDtos(List<RecipeBoard> recipeBoards) {
        if (recipeBoards == null) return null;

        List<RecipeBoardDto.Response> list = new ArrayList<>(recipeBoards.size());
        for (RecipeBoard recipeBoard : recipeBoards) {
            list.add(recipeBoardToRecipeBoardResponseDto(recipeBoard));
        }

        return list;
    }

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
        String subCategoryName = findSubMenuCategory != null ? findSubMenuCategory.getSubMenuCategoryName() : null;
        response.setMenuSubCategory(subCategoryName);
        return response;
    }
}