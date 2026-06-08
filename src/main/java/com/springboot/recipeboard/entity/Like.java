package com.springboot.recipeboard.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long likeId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "recipe_board_id")
    private RecipeBoard recipeBoard;

    public void setMember(Member member) {
        this.member = member;
        if (!member.getLikes().contains(this)) {
            member.setLikes(this);
        }
    }

    public void setRecipeBoard(RecipeBoard recipeBoard) {
        this.recipeBoard = recipeBoard;
        if (!recipeBoard.getLike().contains(this)) {
            recipeBoard.setLike(this);
        }
    }
}