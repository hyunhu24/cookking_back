package com.springboot.bookmark.entitiy;

import com.springboot.audit.BaseEntity;
import com.springboot.member.entity.Member;
import com.springboot.recipeboard.entity.RecipeBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Bookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookmarkId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "recipe_board_id")
    private RecipeBoard recipeBoard;

    public void setMember(Member member) {
        this.member = member;
        if (!member.getBookmarks().contains(this)) {
            member.setBookmark(this);
        }
    }

    public void setRecipeBoard(RecipeBoard recipeBoard) {
        this.recipeBoard = recipeBoard;
        if (!recipeBoard.getBookmarks().contains(this)) {
            recipeBoard.setBookmark(this);
        }
    }
}
