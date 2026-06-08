package com.springboot.file.controller;

import com.springboot.dto.SingleResponseDto;
import com.springboot.file.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Tag(name = "이미지 업로드 컨트롤러", description = "이미지 파일 업로드 API")
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageUploadController {

    private final StorageService storageService;

    @Operation(
            summary = "이미지 업로드",
            description = "이미지를 업로드하고 해당 이미지의 URL을 반환합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "이미지 업로드 성공",
                            content = @Content(schema = @Schema(implementation = SingleResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청")
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(
            @Parameter(description = "업로드할 이미지 파일", required = true)
            @RequestPart MultipartFile file) {

        // 저장 (파일 이름에 UUID 붙이기)
        String fileName = "image_" + UUID.randomUUID();
        String imageUrl = storageService.store(file, fileName);

        return ResponseEntity.ok(new SingleResponseDto<>(imageUrl));
    }
}