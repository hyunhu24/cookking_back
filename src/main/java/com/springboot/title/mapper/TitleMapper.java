package com.springboot.title.mapper;

import com.springboot.challenge.entity.ChallengeLevel;
import com.springboot.member.entity.MemberTitle;
import com.springboot.title.dto.TitleDto;
import com.springboot.title.entity.Title;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TitleMapper {
    default TitleDto.Response titleToTitleResponseDto(Title title, long memberId) {
        TitleDto.Response response = new TitleDto.Response();
        response.setTitleId(title.getTitleId());

        TitleDto.SingleTitleResponse singleTitleResponse = new TitleDto.SingleTitleResponse();
        int level = title.getLevel();
        singleTitleResponse.setLevel(level);
        singleTitleResponse.setType(title.getType());
        singleTitleResponse.setImagePath(title.getImagePath());
        singleTitleResponse.setName(title.getName());
        response.setTitle(singleTitleResponse);

        String description = title.getChallengeCategory().getChallengeLevels().stream()
                .filter(el -> el.getLevel() == level)
                .map(el -> el.getDescription())
                .findFirst().orElse(null);

        response.setDescription(description);
        response.setCategory(title.getChallengeCategory().getName());

        Optional<MemberTitle> OptionalMemberTitle = title.getMemberTitles().stream()
                .filter(memberTitle -> memberTitle.getMember().getMemberId() == memberId)
                .findFirst();
        response.setOwned(OptionalMemberTitle.isPresent());
        return response;
    }

    default List<TitleDto.Response> titlesToTitleResponseDtos(List<Title> titles, long memberId) {
        return titles.stream()
                .map(title -> titleToTitleResponseDto(title, memberId))
                .collect(Collectors.toList());
    }
}