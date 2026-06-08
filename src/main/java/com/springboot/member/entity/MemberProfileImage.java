package com.springboot.member.entity;

import com.springboot.profile.entity.ProfileImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class MemberProfileImage {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long memberProfileImageId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "profile_image_id")
    private ProfileImage profileImage;

    public void setMember(Member member) {
        this.member = member;
        if (!member.getMemberProfileImages().contains(this)) {
            member.setMemberProfileImage(this);
        }
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
        if (!profileImage.getMemberProfileImages().contains(this)) {
            profileImage.setMemberProfileImages(this);
        }
    }
}