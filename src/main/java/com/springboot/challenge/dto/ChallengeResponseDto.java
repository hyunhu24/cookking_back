package com.springboot.challenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ChallengeResponseDto {
    private Long challengeId;
    private String name;
    private String category;
    private String imagePath;
    private int currentLevel;
    private int currentCount;
    private int goalCount;
    private String description;
    private int maxLevel;
}
