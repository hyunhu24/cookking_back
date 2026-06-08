package com.springboot.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyRecipeBoardResponse {
    private long recipeBoardId;
    private String title;
    private String menuName;
    private String image;
    private int likeCount;
    private List<String> ingredients;
}
