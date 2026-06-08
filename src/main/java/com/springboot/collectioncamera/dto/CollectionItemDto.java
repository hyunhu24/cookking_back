package com.springboot.collectioncamera.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CollectionItemDto {
    @Getter
    public static class Post {
        @NotBlank(message = "메뉴 이름은 필수입니다.")
        @Schema(description = "도감에 등록할 음식 이름", example = "공기밥")
        private String menuName;

        @NotBlank(message = "이미지 URL은 필수입니다.")
        @Schema(description = "도감에 등록할 음식 이미지 URL", example = "https://s3.bucket/collections/item_123.png")
        private String imageUrl;
    }

    @Getter
    @Builder
    public static class Response {
        private long collectionItemId;
        private String menuName;
        private String imageUrl;
    }
}
