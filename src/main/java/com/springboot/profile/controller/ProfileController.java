package com.springboot.profile.controller;

import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.member.entity.MemberProfileImage;
import com.springboot.profile.dto.ProfileDto;
import com.springboot.profile.entity.ProfileImage;
import com.springboot.profile.mapper.ProfileMapper;
import com.springboot.profile.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "프로필", description = "프로필 관련 API")
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    // 프로필 조회
//    @Operation(summary = "프로필 조회", description = "프로필 ID로 단일 프로필 이미지를 조회합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "프로필 조회 성공"),
//            @ApiResponse(responseCode = "404", description = "프로필을 찾을 수 없음")
//    })
//    @GetMapping("/{profile-id}")
//    public ResponseEntity getProfile(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
//                                     @PathVariable(value = "profile-id") Long profileId) {
//        ProfileImage profileImage = profileService.findProfileImage(profileId);
//
//        return new ResponseEntity(new SingleResponseDto<>(profileMapper.ProfileImageToProfileResponseDto(profileImage)), HttpStatus.OK);
//    }

    // 프로필 구매
    @Operation(summary = "프로필 이미지 구매", description = "회원이 프로필 이미지를 구매합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로필 구매 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 또는 밥풀이 부족함")
    })
    @PostMapping("{profile-id}/purchase")
    public ResponseEntity purchaseProfile(@PathVariable("profile-id") long profileId,
                                          @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        profileService.purchaseProfile(member.getMemberId(), profileId);
        return ResponseEntity.ok().build();
    }

    // 프로필 착용
    @Operation(summary = "프로필 착용", description = "보유한 프로필을 착용합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로필 착용 성공"),
            @ApiResponse(responseCode = "400", description = "프로필 미보유 또는 예외 발생")
    })
    @PostMapping("/{profile-id}/equip")
    public ResponseEntity equipProfile(@PathVariable("profile-id") Long profileId,
                                       @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        profileService.equipProfile(member.getMemberId(), profileId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "전체 프로필 조회", description = "전체 프로필 이미지 중 보유 여부를 포함하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "전체 프로필 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @GetMapping
    public ResponseEntity getAllProfilesWithOwnership(
            @Parameter(hidden = true) @AuthenticationPrincipal Member member,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ProfileImage> profileImages = profileService.findAllProfileImages(page - 1, size);
        List<ProfileDto.OwnershipResponse> responses =
                profileMapper.profileImagesToOwnershipDtos(profileImages.getContent(), member.getMemberId());

        return new ResponseEntity(new MultiResponseDto<>(responses, profileImages), HttpStatus.OK);
    }
}