package com.springboot.challenge.mapper;

import com.springboot.challenge.dto.ChallengeResponseDto;
import com.springboot.challenge.entity.ChallengeCategory;
import com.springboot.member.entity.MemberChallenge;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {
    List<ChallengeResponseDto> challengesToChallengeResponseDtos(List<MemberChallenge> challengeCategories);

    default ChallengeResponseDto challengeToChallengeResponseDto(MemberChallenge challengeCategory) {
        ChallengeResponseDto challengeResponseDto = new ChallengeResponseDto();

        challengeResponseDto.setChallengeId(challengeCategory.getChallengeCategory().getChallengeCategoryid());
        challengeResponseDto.setName(challengeCategory.getChallengeCategory().getName());
        challengeResponseDto.setCategory(challengeCategory.getChallengeCategory().getCategory());
        // ✅ maxLevel 추가
        challengeResponseDto.setMaxLevel(challengeCategory.getChallengeCategory().getMaxLevel());

        // ✅ 초보자는 따로 처리
        if ("초보자".equals(challengeCategory.getChallengeCategory().getCategory())) {
            challengeResponseDto.setCurrentLevel(0);
            challengeResponseDto.setCurrentCount(0);
            challengeResponseDto.setGoalCount(0);

            // 뉴비용 ChallengeLevel이 있음 → level=0
            challengeCategory.getChallengeCategory()
                    .getChallengeLevels().stream()
                    .filter(level -> level.getLevel() == 0)
                    .findFirst()
                    .ifPresent(level -> {
                        challengeResponseDto.setDescription(level.getDescription());
                        challengeResponseDto.setImagePath(level.getImagePath());
                    });

            return challengeResponseDto;
        }

        challengeResponseDto.setCurrentLevel(challengeCategory.getCurrentLevel());
        challengeResponseDto.setCurrentCount(challengeCategory.getCurrentCount());


        // ✅ 현재 레벨 기준 goal, 설명, 이미지 지정
        challengeCategory.getChallengeCategory()
                .getChallengeLevels().stream()
                .filter(level -> level.getLevel() == challengeCategory.getCurrentLevel())
                .findFirst()
                .ifPresent(level -> {
                    challengeResponseDto.setGoalCount(level.getGoalCount());
                    challengeResponseDto.setDescription(level.getDescription());
                    challengeResponseDto.setImagePath(level.getImagePath());
                });

        return challengeResponseDto;
    }
}