package com.springboot.challenge.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.member.entity.MemberChallenge;
import com.springboot.title.entity.Title;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChallengeCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengeCategoryid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int maxLevel;

    @OneToMany(mappedBy = "challengeCategory", cascade = CascadeType.PERSIST)
    private List<MemberChallenge> memberChallenges = new ArrayList<>();

    @OneToMany(mappedBy = "challengeCategory", cascade = CascadeType.PERSIST)
    private List<ChallengeLevel> challengeLevels = new ArrayList<>();

    @OneToMany(mappedBy = "challengeCategory", cascade = CascadeType.PERSIST)
    private List<Title> titles = new ArrayList<>();

    public void setTitles(Title title) {
        titles.add(title);
        if (title.getChallengeCategory() != this) {
            title.setChallengeCategory(this);
        }
    }

    public void setChallengeLevels(ChallengeLevel challengeLevel) {
        challengeLevels.add(challengeLevel);
        if(challengeLevel.getChallengeCategory() != this) {
            challengeLevel.setChallengeCategory(this);
        }
    }

    public void setMemberChallenges(MemberChallenge memberChallenge) {
        memberChallenges.add(memberChallenge);
        if (memberChallenge.getChallengeCategory() != this) {
            memberChallenge.setChallengeCategory(this);
        }
    }
}