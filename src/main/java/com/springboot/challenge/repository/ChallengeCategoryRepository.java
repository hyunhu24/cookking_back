package com.springboot.challenge.repository;

import com.springboot.challenge.entity.ChallengeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeCategoryRepository extends JpaRepository<ChallengeCategory, Long> {
    Optional<ChallengeCategory> findByCategory(String category);
}