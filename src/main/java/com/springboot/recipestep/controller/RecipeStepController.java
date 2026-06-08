package com.springboot.recipestep.controller;

import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import com.springboot.recipestep.dto.RecipeStepDto;
import com.springboot.recipestep.entity.RecipeStep;
import com.springboot.recipestep.mapper.RecipeStepMapper;
import com.springboot.recipestep.service.RecipeStepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@Tag(name = "레시피 스텝 컨트롤러", description = "레시피 공통 단계 컨트롤러")
@RestController
@RequestMapping("/recipe-steps")
public class RecipeStepController {
    private static final String RECIPE_STEP_DEFAULT_URL = "recipe-steps/";
    private RecipeStepService recipeStepService;
    private RecipeStepMapper mapper;

    public RecipeStepController(RecipeStepService recipeStepService, RecipeStepMapper mapper) {
        this.recipeStepService = recipeStepService;
        this.mapper = mapper;
    }

    @Operation(summary = "레시피 스텝 등록", description = "공통으로 사용하는 레시피 스텝을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "레시피 스텝 등록 완료")
    })
    @PostMapping
    public ResponseEntity postRecipeStep(@RequestBody RecipeStepDto.post recipeStepDto) {
        RecipeStep recipeStep = mapper.recipeStepPostDtoToRecipeStep(recipeStepDto);
        RecipeStep createdRecipeStep = recipeStepService.createRecipeStep(recipeStep);
        URI location = URI.create(RECIPE_STEP_DEFAULT_URL + createdRecipeStep.getRecipeStepId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "레시피 스텝 단일 조회", description = "레시피 스텝 ID를 기준으로 단일 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "레시피 스텝 조회 완료"),
            @ApiResponse(responseCode = "404", description = "레시피 스텝을 찾을 수 없음")
    })
    @GetMapping("/{recipeStepId}")
    public ResponseEntity getRecipeStep(@PathVariable long recipeStepId) {
        RecipeStep recipeStep = recipeStepService.findRecipeStep(recipeStepId);
        RecipeStepDto.Response response = mapper.recipeStepToRecipeStepResponseDto(recipeStep);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Operation(summary = "레시피 스텝 전체 조회", description = "레시피 스텝 전체 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "레시피 스텝 전체 조회 완료")
    })
    @GetMapping
    public ResponseEntity getRecipeSteps(@Positive @RequestParam int page,
                                         @Positive @RequestParam int size) {
        Page<RecipeStep> pageRecipeSteps = recipeStepService.findRecipeSteps(page - 1, size);
        List<RecipeStep> recipeSteps = pageRecipeSteps.getContent();

        return new ResponseEntity<>
                (new MultiResponseDto<>(mapper.recipeStepsToRecipeStepResponseDtos(recipeSteps),
                        pageRecipeSteps),
                        HttpStatus.OK);
    }

    @Operation(summary = "레시피 스텝 수정", description = "레시피 스텝 내용을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "레시피 스텝 수정 완료"),
            @ApiResponse(responseCode = "404", description = "레시피 스텝을 찾을 수 없음")
    })
    @PatchMapping("/{recipeStepId}")
    public ResponseEntity patchRecipeStep(@PathVariable long recipeStepId, @RequestBody RecipeStepDto.patch recipeStepDto) {
        recipeStepDto.setRecipeStepId(recipeStepId);
        RecipeStep recipeStep = mapper.recipeStepPatchDtoToRecipeStep(recipeStepDto);
        RecipeStep updatedRecipeStep = recipeStepService.updateRecipeStep(recipeStep);
        RecipeStepDto.Response response = mapper.recipeStepToRecipeStepResponseDto(updatedRecipeStep);

        return new ResponseEntity(new SingleResponseDto(response), HttpStatus.OK);
    }

    @Operation(summary = "레시피 스텝 삭제", description = "레시피 스텝 ID를 기준으로 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "레시피 스텝 삭제 완료"),
            @ApiResponse(responseCode = "404", description = "레시피 스텝을 찾을 수 없음")
    })
    @DeleteMapping("/{recipeStepId}")
    public ResponseEntity deleteRecipeStep(@PathVariable long recipeStepId) {
        recipeStepService.deleteRecipeStep(recipeStepId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
