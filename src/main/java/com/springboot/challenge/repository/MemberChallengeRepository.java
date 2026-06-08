package com.springboot.challenge.repository;

import com.springboot.member.entity.MemberChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberChallengeRepository extends JpaRepository<MemberChallenge, Long> {
    Page<MemberChallenge> findByMember_MemberId(Long memberId, Pageable pageable);
    Optional<MemberChallenge> findByMember_MemberIdAndChallengeCategory_ChallengeCategoryid(Long memberId, Long challengeCategoryId);
}