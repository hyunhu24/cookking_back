package com.springboot.member.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.bookmark.entitiy.Bookmark;
import com.springboot.collectioncamera.entity.CollectionCamera;
import com.springboot.payment.entity.Payment;
import com.springboot.recipeboard.entity.Like;
import com.springboot.recipeboard.entity.RecipeBoard;
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
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String profile;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Column
    private long activeTitleId;

    @Column(nullable = false)
    private int ricePoint = 0; // 현재 보유 밥풀

    @Column(nullable = false)
    private long activeImageId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<CollectionCamera> collectionCameras = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<RecipeBoard> recipeBoards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<MemberTitle> memberTitles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<MemberChallenge> memberChallenges = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST})
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST})
    private List<MemberProfileImage> memberProfileImages = new ArrayList<>();

    public void setLikes(Like like) {
        likes.add(like);
        if (like.getMember() != this) {
            like.setMember(this);
        }
    }

    public void setMemberProfileImage(MemberProfileImage memberProfileImage) {
        memberProfileImages.add(memberProfileImage);
        if (memberProfileImage.getMember() != this) {
            memberProfileImage.setMember(this);
        }
    }
    public void setCollectionCamera(CollectionCamera collectionCamera) {
        collectionCameras.add(collectionCamera);
        if (collectionCamera.getMember() != this) {
            collectionCamera.setMember(this);
        }
    }

    public void setRecipeBoard(RecipeBoard recipeBoard) {
        recipeBoards.add(recipeBoard);
        if (recipeBoard.getMember() != this) {
            recipeBoard.setMember(this);
        }
    }

    public void setBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
        if (bookmark.getMember() != this) {
            bookmark.setMember(this);
        }
    }

    public void setMemberTitle(MemberTitle memberTitle) {
        memberTitles.add(memberTitle);
        if (memberTitle.getMember() != this) {
            memberTitle.setMember(this);
        }
    }

    public void setMemberChallenge(MemberChallenge memberChallenge) {
        memberChallenges.add(memberChallenge);
        if (memberChallenge.getMember() != this) {
            memberChallenge.setMember(this);
        }
    }

    public void setPayment(Payment payment) {
        payments.add(payment);
        if (payment.getMember() != this) {
            payment.setMember(this);
        }
    }

    public enum MemberStatus {
        MEMBER_ACTIVE("활동 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
