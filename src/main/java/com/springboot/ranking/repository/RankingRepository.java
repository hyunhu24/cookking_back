package com.springboot.ranking.repository;

import com.springboot.member.entity.Member;
import com.springboot.ranking.dto.RankingResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT " +
            "m.member_id AS memberId, " +
            "m.nick_name AS nickName, " +
            "m.profile AS profileImagePath, " +
            "COUNT(r.recipe_board_id) AS count, " +
            "MIN(r.created_at) AS firstRecipeCreatedAt " +
            "FROM member m " +
            "JOIN recipe_board r ON m.member_id = r.member_id " +
            "GROUP BY m.member_id, m.nick_name, m.profile " +
            "HAVING COUNT(r.recipe_board_id) > 0 " +
            "ORDER BY count DESC, firstRecipeCreatedAt ASC",
            nativeQuery = true)
    List<Object[]> findTopMembersByRecipeBoardCount();


    @Query(value = "SELECT " +
            "m.member_id AS memberId, " +
            "m.nick_name AS nickName, " +
            "m.profile AS profileImagePath, " +
            "COUNT(l.like_id) AS likeCount, " +
            "MIN(l.created_at) AS firstLikeCreatedAt " +
            "FROM member m " +
            "JOIN recipe_board r ON m.member_id = r.member_id " +
            "JOIN likes l ON l.recipe_board_id = r.recipe_board_id " +
            "GROUP BY m.member_id, m.nick_name, m.profile " +
            "HAVING COUNT(l.like_id) > 0 " +
            "ORDER BY likeCount DESC, firstLikeCreatedAt ASC " +
            "LIMIT 30", // Pageable 쓰지 않고 LIMIT 30 명시
            nativeQuery = true)
    List<Object[]> findTopMembersByLikeCount();

    @Query(value = "SELECT " +
            "m.member_id AS memberId, " +
            "m.nick_name AS nickName, " +
            "m.profile AS profileImagePath, " +
            "COUNT(b.bookmark_id) AS bookmarkCount, " +
            "MIN(b.created_at) AS firstBookmarkCreatedAt " +
            "FROM member m " +
            "JOIN recipe_board r ON m.member_id = r.member_id " +
            "JOIN bookmark b ON b.recipe_board_id = r.recipe_board_id " +
            "GROUP BY m.member_id, m.nick_name, m.profile " +
            "HAVING COUNT(b.bookmark_id) > 0 " +
            "ORDER BY bookmarkCount DESC, firstBookmarkCreatedAt ASC " +
            "LIMIT 30",
            nativeQuery = true)
    List<Object[]> findTopMembersByBookmarkCount();

    @Query(value = "SELECT " +
            "m.member_id AS memberId, " +
            "m.nick_name AS nickName, " +
            "m.profile AS profileImagePath, " +
            "COUNT(mt.title_id) AS titleCount, " +
            "MAX(mt.created_at) AS lastTitleAcquiredAt " +
            "FROM member m " +
            "JOIN member_title mt ON m.member_id = mt.member_id " +
            "GROUP BY m.member_id, m.nick_name, m.profile " +
            "HAVING COUNT(mt.title_id) > 0 " +
            "ORDER BY titleCount DESC, lastTitleAcquiredAt ASC " +
            "LIMIT 30",
            nativeQuery = true)
    List<Object[]> findTopMembersByTitleCount();

    // Member ID로 랭킹 조회
    @Query(value = "SELECT ranking FROM ( " +
            "SELECT m.member_id, RANK() OVER (ORDER BY COUNT(r.recipe_board_id) DESC, MIN(r.created_at) ASC) AS ranking " +
            "FROM member m " +
            "JOIN recipe_board r ON m.member_id = r.member_id " +
            "GROUP BY m.member_id " +
            ") ranked " +
            "WHERE ranked.member_id = :memberId", nativeQuery = true)
    Integer findRecipeBoardRankByMemberId(@Param("memberId") long memberId);

    @Query(value = "SELECT ranking FROM ( " +
            "SELECT m.member_id, RANK() OVER (ORDER BY COUNT(l.like_id) DESC, MIN(l.created_at) ASC) AS ranking " +
            "FROM member m " +
            "JOIN recipe_board r ON m.member_id = r.member_id " +
            "JOIN likes l ON l.recipe_board_id = r.recipe_board_id " +
            "GROUP BY m.member_id " +
            ") ranked " +
            "WHERE ranked.member_id = :memberId", nativeQuery = true)
    Integer findLikeRankByMemberId(@Param("memberId") long memberId);

    @Query(value = "SELECT ranking FROM ( " +
            "SELECT m.member_id, RANK() OVER (ORDER BY COUNT(b.bookmark_id) DESC, MIN(b.created_at) ASC) AS ranking " +
            "FROM member m " +
            "JOIN recipe_board r ON m.member_id = r.member_id " +
            "JOIN bookmark b ON b.recipe_board_id = r.recipe_board_id " +
            "GROUP BY m.member_id " +
            ") ranked " +
            "WHERE ranked.member_id = :memberId", nativeQuery = true)
    Integer findBookmarkRankByMemberId(@Param("memberId") long memberId);

    @Query(value = "SELECT ranking FROM ( " +
            "SELECT m.member_id, RANK() OVER (ORDER BY COUNT(mt.title_id) DESC, MAX(mt.created_at) ASC) AS ranking " +
            "FROM member m " +
            "JOIN member_title mt ON m.member_id = mt.member_id " +
            "GROUP BY m.member_id " +
            ") ranked " +
            "WHERE ranked.member_id = :memberId", nativeQuery = true)
    Integer findTitleRankByMemberId(@Param("memberId") long memberId);

}