package com.springboot.member.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.challenge.entity.ChallengeCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MemberChallenge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberChallengeId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "challenge_category_id")
    private ChallengeCategory challengeCategory;

    @JoinColumn(nullable = false)
    private int currentLevel = 1; // 현재 레벨

    @JoinColumn(nullable = false)
    private int currentCount = 0; // 현재 카운트

    public void setMember(Member member) {
        this.member = member;
        if (!member.getMemberChallenges().contains(this)) {
            member.setMemberChallenge(this);
        }
    }
    public void setChallengeCategory(ChallengeCategory challengeCategory) {
        this.challengeCategory = challengeCategory;
        if (!challengeCategory.getMemberChallenges().contains(this)) {
            challengeCategory.setMemberChallenges(this);
        }
    }
}