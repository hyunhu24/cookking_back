package com.springboot.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberChallengeDto {
    private long challengeId;
    private String challengeCategoryName;
    private String description;
    private int goalCount;
    private int currentCount;  // 진행도
    private String status;     // COMPLETE or INCOMPLETE
    private String titleName;
}
