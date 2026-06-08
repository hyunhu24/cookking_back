package com.springboot.collectioncamera.controller;

import com.springboot.collectioncamera.dto.CollectionCameraDto;
import com.springboot.collectioncamera.dto.CollectionItemDto;
import com.springboot.collectioncamera.entity.CameraImage;
import com.springboot.collectioncamera.entity.CollectionCamera;
import com.springboot.collectioncamera.entity.CollectionItem;
import com.springboot.collectioncamera.mapper.CollectionCameraMapper;
import com.springboot.collectioncamera.service.CollectionCameraService;
import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.utils.UriCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "도감 컨트롤러", description = "도감 관련 컨트롤러")
@RestController
@RequestMapping("/collections")
@Validated
public class CollectionCameraController {
    private static final String COLLECTION_DEFAULT_URL = "/collections";
    private final CollectionCameraService collectionCameraService;
    private final CollectionCameraMapper mapper;

    public CollectionCameraController(CollectionCameraService collectionCameraService, CollectionCameraMapper mapper) {
        this.collectionCameraService = collectionCameraService;
        this.mapper = mapper;
    }

    @Operation(summary = "도감 카테고리 생성", description = "도감 카테고리를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "도감 카테고리 생성 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @PostMapping
    public ResponseEntity postCollectionCamera(@Valid @RequestBody CollectionCameraDto.Post collectionPostDto,
                                               @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        CollectionCamera collectionCamera = mapper.collectionCameraPostDtoToCollectionCamera(collectionPostDto);

        // 서비스에서 CameraImageId를 기반으로 CameraImage 세팅
        CollectionCamera savedCollection = collectionCameraService.createCollectionCamera(
                collectionCamera,
                collectionPostDto.getCameraImageId(),
                member.getMemberId(),
                collectionPostDto.getIsPrivate()
        );
        CollectionCameraDto.Response responseDto = mapper.collectionCameraToCollectionCameraResponseDto(savedCollection);

        URI location = UriCreator.createUri("/collections", savedCollection.getCollectionCameraId());
        return ResponseEntity.created(location).body(new SingleResponseDto<>(responseDto));
    }

    @Operation(summary = "도감 카테고리 이름, 공개여부 수정", description = "도감 카테고리 이름과 공개여부를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도감 카테고리 이름 수정 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @PatchMapping("/{collection-id}/info")
    public ResponseEntity patchCollectionInfo(@PathVariable("collection-id") @Positive long collectionId,
                                              @Valid @RequestBody CollectionCameraDto.PatchInfo patchInfoDto,
                                              @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        collectionCameraService.updateCollectionInfo(collectionId, patchInfoDto, member.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "도감 카메라 이미지 수정", description = "도감 카메라 이미지를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도감 카메라 이미지 수정 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @PatchMapping("/{collection-id}/camera")
    public ResponseEntity patchCollectionCamera(@PathVariable("collection-id") @Positive long collectionId,
                                                @Valid @RequestBody CollectionCameraDto.PatchCamera patchCameraDto,
                                                @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        collectionCameraService.updateCollectionCamera(collectionId, patchCameraDto.getCameraImageId(), member.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "도감 카테고리 전체 조회", description = "도감 카테고리를 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도감 카테고리 전체 조회 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @GetMapping
    public ResponseEntity getCollection(@Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        // 도감 카테고리 전체 조회 로직 작성 해야함

        List<CollectionCamera> collectionCameras = collectionCameraService.findCollections(member.getMemberId());
        List<CollectionCameraDto.Response> response = collectionCameras.stream()
                .map(mapper::collectionCameraToCollectionCameraResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "도감 카테고리 삭제", description = "도감 카테고리를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도감 카테고리 삭제 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @DeleteMapping("/{collection-id}")
    public ResponseEntity deleteCollection(@PathVariable("collection-id") @Positive long collectionId,
                                           @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        collectionCameraService.deleteCollection(collectionId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "도감 카테고리 내 메뉴 전체 조회", description = "도감 카테고리 내 메뉴들을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도감 카테고리 내 메뉴 전체 조회 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @GetMapping("/{collection-id}/collectionitem")
    public ResponseEntity getCollectionItem(@PathVariable("collection-id") @Positive long collectionId,
                                            @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        List<CollectionItem> collectionItems = collectionCameraService.findCollectionItems(collectionId, member.getMemberId());
        List<CollectionItemDto.Response> response = collectionItems.stream()
                .map(mapper::collectionItemToCollectionItemResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "도감 카테고리 메뉴 추가", description = "도감 카테고리 메뉴를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "도감 카테고리 메뉴 추가 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @PostMapping("/{collection-id}/collectionitem")
    public ResponseEntity postCollectionItem(
            @PathVariable("collection-id") @Positive long collectionId,
            @RequestBody @Valid CollectionItemDto.Post collectionItemPostDto,
            @Parameter(hidden = true) @AuthenticationPrincipal Member member) {

        CollectionItem collectionItem = mapper.collectionItemPostDtoToCollectionItem(collectionItemPostDto);

        CollectionItem postItem = collectionCameraService.addCollectionItem(
                collectionId,
                collectionItem,
                collectionItemPostDto.getImageUrl(),  // 이미지 URL 넘김
                member.getMemberId()
        );

        CollectionItemDto.Response response = mapper.collectionItemToCollectionItemResponseDto(postItem);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "도감 카테고리 메뉴 삭제", description = "도감 카테고리 메뉴를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "도감 카테고리 메뉴 삭제 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @DeleteMapping("/collectionitems/{collectionitem-id}")
    public ResponseEntity deleteCollectionItem(@PathVariable("collectionitem-id") @Positive long collectionItemId,
                                               @Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        collectionCameraService.deleteCollectionItem(collectionItemId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "도감 카메라 색상 별 조회", description = "도감 카메라를 색상 별로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "도감 카메라 색상 별 조회 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @GetMapping("/camera-image")
    public ResponseEntity getCameraImagesByColor(@RequestParam("cameraColorId") @Positive Long cameraColorId) {
        List<CameraImage> cameraImages = collectionCameraService.findCameraImagesByColor(cameraColorId);

        // 🔥 Mapper로 DTO 변환
        List<CollectionCameraDto.ResponseImage> responseDtos = mapper.cameraImagesToCameraImageResponseDtos(cameraImages);

        return ResponseEntity.ok(responseDtos);
    }

    @Operation(summary = "특정 회원의 도감 카테고리 및 해당된 아이템 리스트 전체 조회", description = "특정 회원의 도감 카테고리 및 해당된 아이템 리스트를 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도감 카테고리 전체 조회 완료"),
            @ApiResponse(responseCode = "400", description = "Collection Validation failed")
    })
    @GetMapping("/members/{member-id}")
    public ResponseEntity getMemberCollection(@Parameter(description = "조회할 회원 식별자")
                                              @PathVariable("member-id") @Positive long memberId) {
        // 도감 카테고리 전체 조회 로직 작성 해야함

        List<CollectionCamera> collectionCameras = collectionCameraService.findCollections(memberId);
        List<CollectionCameraDto.ResponseCamera> response = mapper.collectionCameraToCollectionCameraResponseDtos(collectionCameras);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}