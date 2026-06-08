package com.springboot.recipeboard.repository;

import com.springboot.bookmark.entitiy.Bookmark;
import com.springboot.ingredient.entity.Ingredient;
import com.springboot.member.entity.Member;
import com.springboot.recipeboard.entity.RecipeBoard;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeBoardRepository extends JpaRepository<RecipeBoard, Long> {
    Page<RecipeBoard> findByMemberAndRecipeBoardStatusNot(Member member, RecipeBoard.RecipeBoardStatus status, Pageable pageable);
    Page<RecipeBoard> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<RecipeBoard> findByMenu_MenuCategory_MenuCategoryId(long menuMenuCategoryMenuCategoryId, Pageable pageable);
    Page<RecipeBoard> findByMenu_MenuId(long menuMenuId, Pageable pageable);
    Page<RecipeBoard> findByBookmarks_Member_MemberId(long memberId, Pageable pageable);
    Page<RecipeBoard> findByLike_Member_MemberId(long memberId, Pageable pageable);
    Page<RecipeBoard> findByMember_MemberId(long memberId, Pageable pageable);
    Page<RecipeBoard> findByMember_MemberIdAndTitleContaining(long memberId, String title, Pageable pageable);
    long countByMenu_MenuId(Long menuId);

    @Query(
            value = "SELECT rb.* " +
                    "FROM recipe_board rb " +
                    "JOIN recipe_board_ingredient rbi ON rb.recipe_board_id = rbi.recipe_board_id " +
                    "WHERE rbi.ingredient_id IN (:ingredientIds) " +
                    "GROUP BY rb.recipe_board_id " +
                    "HAVING COUNT(DISTINCT rbi.ingredient_id) = :ingredientCount " +
                    "LIMIT 100",
            nativeQuery = true
    )
    List<RecipeBoard> findMatchedRecipeBoards(
            @Param("ingredientIds") List<Long> ingredientIds,
            @Param("ingredientCount") long ingredientCount
    );

    Page<RecipeBoard> findByMenu_MenuName(String menuName, Pageable pageable);
}