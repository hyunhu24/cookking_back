package com.springboot.recipeboard.repository;

import com.springboot.recipeboard.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMember_MemberIdAndRecipeBoard_RecipeBoardId(long memberId, long recipeBoardId); // 좋아요 여부 확인
    long countByRecipeBoard_Member_MemberId(long memberId);
}