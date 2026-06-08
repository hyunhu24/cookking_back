package com.springboot.collectioncamera.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CameraImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cameraImageId;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "camera_color_id")
    private CameraColor cameraColor;
}
