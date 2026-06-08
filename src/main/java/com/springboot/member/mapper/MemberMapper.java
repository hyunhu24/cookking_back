package com.springboot.member.mapper;

import com.springboot.member.dto.MemberChallengeDto;
import com.springboot.member.dto.MemberDto;
import com.springboot.member.dto.MyPageResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.member.entity.MemberChallenge;
import com.springboot.member.entity.MemberProfileImage;
import com.springboot.member.entity.MemberTitle;
import com.springboot.profile.entity.ProfileImage;
import com.springboot.title.dto.TitleDto;
import com.springboot.title.entity.Title;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//매핑되지 않은 필드가 있더라도 에러를 무시하도록 설정
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    @Mapping(source = "profileImageId", target = "activeImageId")
    Member memberPostToMember(MemberDto.Post memberPostDto);
    @Mapping(source = "profileImageId", target = "activeImageId")
    @Mapping(source = "activeTitleId", target = "activeTitleId")
    Member memberPatchToMember(MemberDto.Patch memberPatchDto);
    Member memberDeleteToMember(MemberDto.Delete memberDeleteDto);
    List<MemberDto.Response> membersToMemberResponses(List<Member> members);
    default MyPageResponseDto memberToMyPageResponseDto(Member member) {
        MyPageResponseDto response = new MyPageResponseDto();
        response.setNickName(member.getNickName());
        response.setRicePoint(member.getRicePoint());
        // 🔥 activeImageId로 프로필 이미지 직접 조회
        ProfileImage activeProfile = member.getMemberProfileImages().stream()
                .map(MemberProfileImage::getProfileImage)
                .filter(img -> img.getProfileImageId() == member.getActiveImageId())
                .findFirst()
                .orElse(null);

        response.setProfile(activeProfile != null ? activeProfile.getImagePath() : null);
        response.setActiveProfileId(member.getActiveImageId());

        Title title = member.getMemberTitles().stream()
                .filter(memberTitle -> memberTitle.getTitle().getTitleId() == member.getActiveTitleId())
                .map(MemberTitle::getTitle)
                .findFirst().orElse(null);

        response.setTitle(title.getName());
        return response;
    }
    MemberChallengeDto memberChallengeToMemberChallengeResponse(MemberChallenge memberChallenge, int currentCount);
    List<MemberChallengeDto> memberChallengesToMemberChallengeResponses(List<MemberChallenge> memberChallenges);
    default MemberDto.Response memberToMemberResponse(Member member) {
        MemberDto.Response response = new MemberDto.Response();
        response.setMemberId(member.getMemberId());
        response.setLoginId(member.getLoginId());
        response.setEmail(member.getEmail());
        response.setNickName(member.getNickName());
        response.setPhoneNumber(member.getPhoneNumber());
        response.setProfileImagePath(member.getProfile());
        response.setActiveTitleId(member.getActiveTitleId());
        response.setActiveProfileId(member.getActiveImageId());
        response.setRicePoint(member.getRicePoint());
        response.setMemberStatus(member.getMemberStatus());

        // 🔥 activeImageId 기준으로 profileImagePath 설정
        ProfileImage activeProfile = member.getMemberProfileImages().stream()
                .map(MemberProfileImage::getProfileImage)
                .filter(img -> img.getProfileImageId() == member.getActiveImageId())
                .findFirst()
                .orElse(null);

        response.setProfileImagePath(activeProfile != null ? activeProfile.getImagePath() : null);


        List<TitleDto.Response> titles = new ArrayList<>();

        member.getMemberTitles().stream()
                .forEach(memberTitle -> {
                    titles.add(titleToTitleResponseDto(memberTitle.getTitle(), member.getMemberId()));
                });
        response.setTitles(titles);
        return response;
    }

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
}