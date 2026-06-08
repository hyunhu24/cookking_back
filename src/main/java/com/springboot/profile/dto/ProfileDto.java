package com.springboot.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProfileDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long profileImageId;
        private String imagePath;
        private int price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class OwnershipResponse {
        private long profileId;
        private String imagePath;
        private int price;
        private boolean isOwned;
    }
}