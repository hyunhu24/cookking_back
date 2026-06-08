package com.springboot.bookmark.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookmarkResponseDto {
    private Long recipeBoardId;
    private String title;
    private String menuName;
    private String imageUrl;
}
