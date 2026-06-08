package com.springboot.collectioncamera.entity;

import com.springboot.audit.BaseEntity;
import com.springboot.member.entity.Member;
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
public class CollectionCamera extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long collectionCameraId;

    @Column(nullable = false)
    private String customCategoryName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CollectionStatus collectionStatus = CollectionStatus.PUBLIC;

    @OneToMany(mappedBy = "collectionCamera", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CollectionItem> collectionItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "camera_image_id")
    private CameraImage cameraImage;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if (!member.getCollectionCameras().contains(this)) {
            member.setCollectionCamera(this);
        }
    }

    public void setCollectionItem(CollectionItem collectionItem) {
        collectionItems.add(collectionItem);
        if (collectionItem.getCollectionCamera() != this) {
            collectionItem.setCollectionCamera(this);
        }
    }

    public enum CollectionStatus {
        PUBLIC("도감 공개"),
        PRIVATE("도감 비공개");

        @Getter
        private String status;

        CollectionStatus(String status) {
            this.status = status;
        }
    }
}
