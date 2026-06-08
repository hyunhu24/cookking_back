package com.springboot.title.controller;

import com.springboot.dto.MultiResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.title.dto.TitleDto;
import com.springboot.title.entity.Title;
import com.springboot.title.mapper.TitleMapper;
import com.springboot.title.service.TitleService;
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

import java.util.List;

@Tag(name = "칭호 컨트롤러", description = "칭호 관련 컨트롤러")
@RestController
@RequestMapping("/titles")
public class TitleController {
    private final TitleService titleService;
    private final TitleMapper mapper;

    public TitleController(TitleService titleService, TitleMapper mapper) {
        this.titleService = titleService;
        this.mapper = mapper;
    }

    @Operation(summary = "모든 타이틀 조회", description = "모든 타이틀을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "타이틀 조회 성공"),
            @ApiResponse(responseCode = "404", description = "타이틀을 찾을 수 없습니다.")
    })
    @GetMapping()
    public ResponseEntity getTitles(@Parameter(hidden = true) @AuthenticationPrincipal Member authenticatedmember,
                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "size", defaultValue = "100") int size) {
        Page<Title> pageTitles = titleService.findTitles(page, size, authenticatedmember.getMemberId());
        List<Title> titles = pageTitles.getContent();
        List<TitleDto.Response> response = mapper.titlesToTitleResponseDtos(titles, authenticatedmember.getMemberId());
        return new ResponseEntity(new MultiResponseDto<>(response, pageTitles), HttpStatus.OK);
    }

    @Operation(summary = "보유한 모든 타이틀 조회", description = "보유하고 있는 타이틀을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "타이틀 조회 성공"),
            @ApiResponse(responseCode = "404", description = "타이틀을 찾을 수 없습니다.")
    })
    @GetMapping("/owned")
    public ResponseEntity getOwnedTitles(@Parameter(hidden = true) @AuthenticationPrincipal Member authenticatedmember,
                                         @RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "size", defaultValue = "100") int size) {
        Page<Title> pageTitles = titleService.findOwnedTitles(page, size, authenticatedmember.getMemberId());
        List<Title> titles = pageTitles.getContent();
        List<TitleDto.Response> response = mapper.titlesToTitleResponseDtos(titles, authenticatedmember.getMemberId());
        return new ResponseEntity(new MultiResponseDto<>(response, pageTitles), HttpStatus.OK);
    }

    @Operation(summary = "회원의 모든 타이틀 조회", description = "회원에 모든 타이틀을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "타이틀 조회 성공"),
            @ApiResponse(responseCode = "404", description = "타이틀을 찾을 수 없습니다.")
    })
    @GetMapping("/members/{member-id}")
    public ResponseEntity getMemberTitles(@Parameter(hidden = true) @AuthenticationPrincipal Member authenticatedmember,
                                          @Parameter(description = "조회할 회원 정보") @PathVariable(value = "member-id") long memberId,
                                          @RequestParam(name = "page", defaultValue = "1") int page,
                                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Title> pageTitles = titleService.findOwnedTitles(page, size, authenticatedmember.getMemberId());
        List<Title> titles = pageTitles.getContent();
        List<TitleDto.Response> response = mapper.titlesToTitleResponseDtos(titles, memberId);
        return new ResponseEntity(new MultiResponseDto<>(response, pageTitles), HttpStatus.OK);
    }

    @Operation(summary = "내가 없는 모든 타이틀 조회", description = "내가 보유하지 않은 타이틀을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "타이틀 조회 성공"),
            @ApiResponse(responseCode = "404", description = "타이틀을 찾을 수 없습니다.")
    })
    @GetMapping("/unowned")
    public ResponseEntity getUnownedTitles(@Parameter(hidden = true) @AuthenticationPrincipal Member authenticatedmember,
                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "size", defaultValue = "100") int size) {
        Page<Title> pageTitles = titleService.findUnownedTitles(page, size, authenticatedmember.getMemberId());
        List<Title> titles = pageTitles.getContent();
        List<TitleDto.Response> response = mapper.titlesToTitleResponseDtos(titles, authenticatedmember.getMemberId());
        return new ResponseEntity(new MultiResponseDto<>(response, pageTitles), HttpStatus.OK);
    }
    // 칭호 착용
    @Operation(summary = "칭호 착용", description = "보유한 칭호를 착용합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "칭호 착용 성공"),
            @ApiResponse(responseCode = "400", description = "보유하지 않은 칭호 또는 예외 발생")
    })
    @PostMapping("/{title-id}/equip")
    public ResponseEntity equipTitle(@PathVariable("title-id") Long titleId,
                                     @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        titleService.equipTitle(member.getMemberId(), titleId);
        return ResponseEntity.ok().build();
    }

}