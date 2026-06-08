package com.springboot.challenge.repository;

import com.springboot.challenge.entity.ChallengeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<ChallengeCategory, Long> {
}
