package com.springboot.member.controller;

import com.springboot.dto.SingleResponseDto;
import com.springboot.member.dto.MemberDto;
import com.springboot.member.entity.Member;
import com.springboot.member.mapper.MemberMapper;
import com.springboot.member.service.MemberService;
import com.springboot.utils.UriCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@Tag(name = "회원 컨트롤러", description = "회원 관련 컨트롤러")
@RestController
@RequestMapping("/members")
@Validated
public class MemberController {
    private static final String MEMBER_DEFAULT_URL = "/members";
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @Operation(summary = "아이디 중복 검증", description = "아이디가 중복인지 검증합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "아이디 중복 검증 완료"),
            @ApiResponse(responseCode = "409", description = "해당 아이디가 이미 존재합니다.")
    })
    @PostMapping("/id")
    public ResponseEntity validateName(@Valid @RequestBody MemberDto.Id loginIdDto) {
        memberService.verifyExistsLoginId(loginIdDto.getLoginId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "닉네임 중복 검증", description = "닉네임이 중복인지 검증합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "닉네임 중복 검증 완료"),
            @ApiResponse(responseCode = "409", description = "해당 닉네임이 이미 존재합니다.")
    })
    @PostMapping("/name")
    public ResponseEntity validateName(@Valid @RequestBody MemberDto.Name nameDto) {
        memberService.verifyExistsName(nameDto.getNickName());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "핸드폰번호 유효성 검증", description = "핸드폰번호가 유효한지 검증합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "핸드폰번호 검증 완료"),
            @ApiResponse(responseCode = "409", description = "해당 핸드폰번호가 이미 존재합니다.")
    })
    @PostMapping("/phone")
    public ResponseEntity validatePhone(@Valid @RequestBody MemberDto.Phone phoneDto) {
        memberService.verifyExistsPhoneNumber(phoneDto.getPhoneNumber());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 가입", description = "회원 가입을 진행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 등록 완료"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @PostMapping
    public ResponseEntity postMember(@RequestBody @Valid MemberDto.Post memberPostDto) {
        // Mapper를 통해 받은 Dto 데이터 Member로 변환
        Member member = mapper.memberPostToMember(memberPostDto);
        Member createdMember = memberService.createMember(member, memberPostDto.getProfileImageId());
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "회원 수정(닉네임)", description = "회원 정보를 수정합니다(닉네임만).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 수정 완료"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @PatchMapping
    public ResponseEntity patchMember(@RequestBody @Valid MemberDto.Patch memberPatchDto,
                                      @Parameter(hidden = true) @AuthenticationPrincipal Member authenticatedmember){
        Member member = mapper.memberPatchToMember(memberPatchDto);

        memberService.updateMember(member, authenticatedmember.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 조회", description = "회원 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 조회 완료"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    @Operation(summary = "회원 탈퇴(자신)", description = "자신(회원)이 탈퇴 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "회원 삭제 완료"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @DeleteMapping
    public ResponseEntity deleteMember(@Valid @RequestBody MemberDto.Delete memberDeleteDto) {
        Member member = mapper.memberDeleteToMember(memberDeleteDto);
        memberService.deleteMember(member);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "아이디 찾기", description = "이메일 인증 후 이메일 + 전화번호로 아이디를 찾습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "아이디 찾기 성공"),
            @ApiResponse(responseCode = "404", description = "해당 회원이 존재하지 않음")
    })
    @PostMapping("/findid")
    public ResponseEntity findLoginId(
            @Valid @RequestBody MemberDto.FindId findIdDto) {

        String loginId = memberService.findLoginId(findIdDto.getEmail(), findIdDto.getPhoneNumber());
        return new ResponseEntity<>(new SingleResponseDto<>(loginId), HttpStatus.OK);
    }

    @Operation(summary = "비밀번호 재설정", description = "이메일 인증 후 로그인 ID + 이메일로 비밀번호를 재설정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 재설정 완료"),
            @ApiResponse(responseCode = "404", description = "해당 회원이 존재하지 않음")
    })
    @PatchMapping("/password")
    public ResponseEntity resetPassword(
            @Valid @RequestBody MemberDto.ResetPassword resetPasswordDto) {

        memberService.resetPassword(
                resetPasswordDto.getLoginId(),
                resetPasswordDto.getEmail(),
                resetPasswordDto.getNewPassword()
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

