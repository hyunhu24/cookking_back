package com.springboot.collectioncamera.repository;

import com.springboot.collectioncamera.entity.CollectionCamera;
import com.springboot.collectioncamera.entity.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {
    List<CollectionItem> findByCollectionCamera(CollectionCamera collectionCamera);
}
