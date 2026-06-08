package com.springboot.collectioncamera.dto;


import com.springboot.collectioncamera.entity.CollectionCamera;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class CollectionCameraDto {
    @Getter
    public static class Post {
        @Schema(description = "사용자 지정 도감 카테고리 이름", example = "실패해서 다시 도전할 음식")
        @NotBlank(message = "도감 카테고리 이름은 필수입니다.")
        @Size(min = 1, max = 20, message = "카테고리 이름은 1자 이상 20자 이내여야 합니다.")
        @Pattern(
                regexp = "^(?!\\s)(?!.*\\s{2,}).*$",
                message = "카테고리 이름은 공백으로 시작하거나 연속된 공백이 포함될 수 없습니다."
        )
        private String customCategoryName;

        // 카메라 이미지 선택 (CameraImageId)
        @Schema(description = "카메라 이미지 ID", example = "1")
        private Long cameraImageId;

        @Schema(description = "도감 공개 여부 (true: 비공개, false: 공개)", example = "false")
        private Boolean isPrivate;
    }

    @Getter
    @Setter
    public static class PatchInfo {
        @Schema(description = "도감 카테고리 이름", example = "실패해서 다시 도전할 음식")
        @NotBlank(message = "도감 카테고리 이름은 필수입니다.")
        @Size(min = 1, max = 20, message = "카테고리 이름은 1자 이상 20자 이내여야 합니다.")
        @Pattern(
                regexp = "^(?!\\s)(?!.*\\s{2,}).*$",
                message = "카테고리 이름은 공백으로 시작하거나 연속된 공백이 포함될 수 없습니다."
        )
        private String customCategoryName;

        @Schema(description = "도감 공개 여부 (true: 비공개, false: 공개)", example = "false")
        private Boolean isPrivate;
    }

    @Getter
    @Setter
    public static class PatchCamera {
        @Schema(description = "카메라 이미지 ID", example = "2")
        @NotNull(message = "카메라 이미지 ID는 필수입니다.")
        private Long cameraImageId;
    }

    @Getter
    @Builder
    public static class Response {
        private long collectionCameraId;
        private String imageUrl;
        private String customCategoryName;
        private String collectionStatus;
    }

    @Getter
    @Setter
    public static class ResponseImage {
        private Long cameraImageId;
        private String imageUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ResponseCamera {
        private long collectionCameraId;
        private String customCategoryName;
        private String cameraImage;
        private String collectionStatus;
        private List<ResponseCollectionItem> responseCollectionItems;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class ResponseCollectionItem {
        private long collectionItemId;
        private String imageUrl;
        private String menuName;
    }
}