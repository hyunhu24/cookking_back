package com.springboot.title.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.challenge.entity.ChallengeCategory;
import com.springboot.member.entity.MemberTitle;
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
public class Title extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long titleId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "challenge_category_id")
    private ChallengeCategory challengeCategory;

    @OneToMany(mappedBy = "title", cascade = CascadeType.PERSIST)
    private List<MemberTitle> memberTitles = new ArrayList<>();

    public void setMemberTitle(MemberTitle memberTitle) {
        memberTitles.add(memberTitle);
        if (memberTitle.getTitle() != this) {
            memberTitle.setTitle(this);
        }
    }

    public void setChallengeCategory(ChallengeCategory challengeCategory) {
        this.challengeCategory = challengeCategory;
        if (!challengeCategory.getTitles().contains(this)) {
            challengeCategory.setTitles(this);
        }
    }
}