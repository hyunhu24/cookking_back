package com.springboot.title.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TitleDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long titleId;
        private SingleTitleResponse title;
        private String description;
        private String category;
        private boolean isOwned;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SingleTitleResponse {
        private String type;
        private String imagePath;
        private int level;
        private String name;
    }
}