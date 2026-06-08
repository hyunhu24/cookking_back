package com.springboot.bookmark.repository;

import com.springboot.bookmark.entitiy.Bookmark;
import com.springboot.member.entity.Member;
import com.springboot.recipeboard.entity.RecipeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Page<RecipeBoard> findRecipeBoardsByMember(Member member, Pageable pageable);
    Optional<Bookmark> findByMemberAndRecipeBoard_RecipeBoardId(Member member, Long recipeBoardId);
    Optional<Bookmark> findByMember_MemberIdAndRecipeBoard_RecipeBoardId(long memberId, Long id);
    long countByRecipeBoard_Member_MemberId(Long memberId);
}
