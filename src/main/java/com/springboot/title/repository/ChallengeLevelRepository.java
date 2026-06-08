package com.springboot.title.repository;

import com.springboot.challenge.entity.ChallengeLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeLevelRepository extends JpaRepository<ChallengeLevel, Long> {
    Optional<ChallengeLevel> findByChallengeCategory_ChallengeCategoryidAndLevel(Long challengeCategoryId, int currentLevel);
}