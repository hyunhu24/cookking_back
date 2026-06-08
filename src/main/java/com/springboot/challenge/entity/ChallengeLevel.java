package com.springboot.challenge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChallengeLevel {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long challengeLevelId;

    // 각 도전과제 마다 일정 횟수 달성 시 레벨을 부여함
    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int goalCount; // 목표 달성 횟수

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "challenge_category_id")
    private ChallengeCategory challengeCategory; // 도전과제와의 관계 설정

    public void setChallengeCategory(ChallengeCategory challengeCategory) {
        this.challengeCategory = challengeCategory;
        if(challengeCategory.getChallengeLevels() != this) {
            challengeCategory.setChallengeLevels(this);
        }
    }
}