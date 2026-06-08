package com.springboot.collectioncamera.repository;

import com.springboot.collectioncamera.entity.CameraImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CameraImageRepository extends JpaRepository<CameraImage, Long> {
    List<CameraImage> findAllByCameraColor_CameraColorId(Long cameraColorId);
}
