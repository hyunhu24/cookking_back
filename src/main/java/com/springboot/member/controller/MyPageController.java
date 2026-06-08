package com.springboot.member.controller;

import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import com.springboot.member.dto.MemberChallengeDto;
import com.springboot.member.dto.MyPageResponseDto;
import com.springboot.member.dto.MyRecipeBoardResponse;
import com.springboot.member.entity.Member;
import com.springboot.member.entity.MemberChallenge;
import com.springboot.member.entity.MemberTitle;
import com.springboot.member.mapper.MemberMapper;
import com.springboot.member.service.MyPageService;
import com.springboot.recipeboard.dto.RecipeBoardDto;
import com.springboot.recipeboard.entity.RecipeBoard;
import com.springboot.recipeboard.mapper.RecipeBoardMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "마이페이지 컨트롤러", description = "마이페이지 관련 컨트롤러")
@RestController
@RequestMapping("/mypage")
@Validated
public class MyPageController {
    private final MemberMapper memberMapper;
    private final RecipeBoardMapper recipeBoardMapper;
    private final MyPageService myPageService;

    public MyPageController(MemberMapper mapper, RecipeBoardMapper recipeBoardMapper, MyPageService myPageService) {
        this.memberMapper = mapper;
        this.recipeBoardMapper = recipeBoardMapper;
        this.myPageService = myPageService;
    }

//    @Operation(summary = "내 정보 조회", description = "내 프로필 사진, 닉네임, 포인트, 착용 칭호 조회")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "내 정보 조회 완료")
//    })
//    @GetMapping
//    public ResponseEntity getMyInfo(@Parameter(hidden = true) @AuthenticationPrincipal Member member) {
//
//        Member findMember = myPageService.findMyInfo(member.getMemberId());
//
//        MyPageResponseDto responseDto = memberMapper.memberToMyPageResponseDto(findMember);
//
//        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.OK);
//    }

    @Operation(summary = "내 북마크 리스트 조회", description = "내가 북마크한 게시글 리스트를 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 북마크 조회 완료")
    })
    @GetMapping("/bookmarks")
    public ResponseEntity getBookmarks(
            @DefaultValue(value = "1") @Positive @RequestParam int page,
            @DefaultValue(value = "30") @Positive @RequestParam int size,
            @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        Page<RecipeBoard> bookmarks = myPageService.findMyBookmarkList(member.getMemberId(), page - 1, size);
        List<RecipeBoardDto.Response> content = bookmarks.getContent().stream()
                .map(recipeBoard -> recipeBoardMapper.recipeBoardToRecipeBoardResponseDto(recipeBoard, member.getMemberId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new MultiResponseDto<>(content, bookmarks));
    }

    @Operation(summary = "내 좋아요 리스트 조회", description = "내가 좋아요한 게시글 리스트를 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 좋아요 조회 완료")
    })
    @GetMapping("/likes")
    public ResponseEntity getLikes(
            @DefaultValue(value = "1") @Positive @RequestParam int page,
            @DefaultValue(value = "30") @Positive @RequestParam int size,
            @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        Page<RecipeBoard> likes = myPageService.findLikeList(member.getMemberId(), page - 1, size);
        List<RecipeBoardDto.Response> content = likes.getContent().stream()
                .map(recipeBoard -> recipeBoardMapper.recipeBoardToRecipeBoardResponseDto(recipeBoard, member.getMemberId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new MultiResponseDto<>(content, likes));
    }

    @Operation(summary = "내 북마크 해제", description = "북마크를 해제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "북마크 해제 완료")
    })
    @DeleteMapping("/bookmark/{recipe-id}")
    public ResponseEntity deleteBookmark(
            @PathVariable("recipe-id") @Positive long recipeBoardId,
            @Parameter(hidden = true) @AuthenticationPrincipal Member member) {

        myPageService.deleteMyBookmark(recipeBoardId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @Operation(summary = "내 보유 칭호 전체 조회", description = "내가 보유한 칭호 전체 조회")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "내 칭호 전체 조회 완료")
//    })
//    @GetMapping("/titles")
//    public ResponseEntity getMyTitles(
//            @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
//
//        List<MemberTitle> titles = myPageService.findMyTitles(member.getMemberId());
//
//        return new ResponseEntity<>(new SingleResponseDto<>(titles), HttpStatus.OK);
//    }

    @Operation(summary = "내 레시피 게시글 조회", description = "내가 작성한 레시피 게시글들을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 레시피 게시글 조회 완료")
    })
    @GetMapping("/recipeboards")
    public ResponseEntity getMyRecipeBoards(
            @DefaultValue(value = "1") @Positive @RequestParam int page,
            @DefaultValue(value = "30") @Positive @RequestParam int size,
            @Parameter(hidden = true) @AuthenticationPrincipal Member member) {

        Page<RecipeBoard> recipeBoardPage = myPageService.findMyRecipeBoards(member.getMemberId(), page - 1, size);
        List<RecipeBoardDto.Response> content = recipeBoardPage.getContent().stream()
                .map(recipeBoard -> recipeBoardMapper.recipeBoardToRecipeBoardResponseDto(recipeBoard, member.getMemberId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new MultiResponseDto<>(content, recipeBoardPage));
    }

    @Operation(summary = "내 레시피 게시글 검색", description = "내가 작성한 레시피 게시글에서 검색하는 기능")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 레시피 게시글 검색 완료")
    })
    @GetMapping("/search")
    public ResponseEntity getSearchMyRecipeBoard(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                                 @RequestParam(value = "keyword", required = false) String keyword,
                                                 @RequestParam @Positive int page,
                                                 @RequestParam @Positive int size) {

        Page<RecipeBoard> recipeBoardPage = myPageService.findMyRecipeAndKeywordBoards(member.getMemberId(), keyword, page - 1, size);
        List<RecipeBoardDto.Response> content = recipeBoardPage.getContent().stream()
                .map(recipeBoard -> recipeBoardMapper.recipeBoardToRecipeBoardResponseDto(recipeBoard, member.getMemberId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new MultiResponseDto<>(content, recipeBoardPage));
    }
}