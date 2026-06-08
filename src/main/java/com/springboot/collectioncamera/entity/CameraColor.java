package com.springboot.collectioncamera.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CameraColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cameraColorId;

    @Column(nullable = false)
    private String cameraColorName;
}
