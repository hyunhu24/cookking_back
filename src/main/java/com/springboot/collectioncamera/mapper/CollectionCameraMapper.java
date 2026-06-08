package com.springboot.collectioncamera.mapper;

import com.springboot.collectioncamera.dto.CollectionCameraDto;
import com.springboot.collectioncamera.dto.CollectionItemDto;
import com.springboot.collectioncamera.entity.CameraImage;
import com.springboot.collectioncamera.entity.CollectionCamera;
import com.springboot.collectioncamera.entity.CollectionItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CollectionCameraMapper {
    CollectionCamera collectionCameraPostDtoToCollectionCamera(CollectionCameraDto.Post Dto);
    List<CollectionCameraDto.Response> collectionsToCollectionResponseDtos(List<CollectionCamera> collectionCameras);
    @Mapping(target = "imageUrl", expression = "java(mapCameraImage(collectionCamera))")
    CollectionCameraDto.Response collectionCameraToCollectionCameraResponseDto(CollectionCamera collectionCamera);
    CollectionItemDto.Response collectionItemToCollectionItemResponseDto(CollectionItem entity);
    CollectionItem collectionItemPostDtoToCollectionItem(CollectionItemDto.Post dto);
    List<CollectionCameraDto.ResponseImage> cameraImagesToCameraImageResponseDtos(List<CameraImage> cameraImages);


    default String mapCameraImage(CollectionCamera collectionCamera) {
        if (collectionCamera.getCameraImage() != null) {
            return collectionCamera.getCameraImage().getImageUrl();
        } else {
            return null; // 이미지 없으면 null
        }
    }
    default List<CollectionCameraDto.ResponseCamera> collectionCameraToCollectionCameraResponseDtos(List<CollectionCamera> collectionCameras) {
        return collectionCameras.stream()
                .map(this::collectionCametaDtoToCollectionCameraResponseDto)
                .collect(Collectors.toList());
    }

    default CollectionCameraDto.ResponseCamera collectionCametaDtoToCollectionCameraResponseDto(CollectionCamera collectionCamera) {
        CollectionCameraDto.ResponseCamera responseCameraDto = new CollectionCameraDto.ResponseCamera();
        responseCameraDto.setCustomCategoryName(collectionCamera.getCustomCategoryName());
        responseCameraDto.setCollectionCameraId(collectionCamera.getCollectionCameraId());
        responseCameraDto.setCollectionStatus(String.valueOf(collectionCamera.getCollectionStatus()));
        responseCameraDto.setCameraImage(collectionCamera.getCameraImage().getImageUrl());
        List<CollectionCameraDto.ResponseCollectionItem> responseCollectionItems = collectionCamera.getCollectionItems()
                .stream()
                .map(item -> {
                    CollectionCameraDto.ResponseCollectionItem responseItem = new CollectionCameraDto.ResponseCollectionItem();
                    responseItem.setCollectionItemId(item.getCollectionItemId());
                    responseItem.setImageUrl(item.getImageUrl());
                    responseItem.setMenuName(item.getMenuName());
                    return responseItem;
                })
                .collect(Collectors.toList());
        responseCameraDto.setResponseCollectionItems(responseCollectionItems);
        return responseCameraDto;
    }
}