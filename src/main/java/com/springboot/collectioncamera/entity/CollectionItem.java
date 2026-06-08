package com.springboot.collectioncamera.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CollectionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long collectionItemId;

    @Column(nullable = false)
    private String menuName; // 음식 이름

    @Column(nullable = false)
    private String imageUrl; // 음식 사진 URL

    @ManyToOne
    @JoinColumn(name = "collection_camera_id")
    private CollectionCamera collectionCamera;

    public void setCollectionCamera(CollectionCamera collectionCamera) {
        this.collectionCamera = collectionCamera;
        if (!collectionCamera.getCollectionItems().contains(this)) {
            collectionCamera.setCollectionItem(this);
        }
    }
}
