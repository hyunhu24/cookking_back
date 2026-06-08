package com.springboot.ingredient.controller;

import com.springboot.dto.MultiResponseDto;
import com.springboot.dto.SingleResponseDto;
import com.springboot.ingredient.dto.IngredientDto;
import com.springboot.ingredient.entity.Ingredient;
import com.springboot.ingredient.mapper.IngredientMapper;
import com.springboot.ingredient.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "재료 컨트롤러", description = "재료 관련 컨트롤러")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper mapper;
    private static final String RECIPE_STEP_DEFAULT_URL = "recipe-steps/";

    public IngredientController(IngredientService ingredientService, IngredientMapper mapper) {
        this.ingredientService = ingredientService;
        this.mapper = mapper;
    }

    @Operation(summary = "재료 등록", description = "새로운 재료를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "재료 등록 완료"),
            @ApiResponse(responseCode = "400", description = "재료 Validation 실패")
    })
    @PostMapping
    public ResponseEntity postIngredient(@RequestBody IngredientDto.Post ingredientPostDto) {
        Ingredient ingredient = mapper.ingredientPostDtoToIngredient(ingredientPostDto);
        Ingredient savedIngredient = ingredientService.createIngredient(ingredient);
        URI location = URI.create(RECIPE_STEP_DEFAULT_URL + savedIngredient.getIngredientId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "재료 수정", description = "기존 재료 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "재료 수정 완료"),
            @ApiResponse(responseCode = "400", description = "재료 Validation 실패")
    })
    @PatchMapping("/{ingredient-id}")
    public ResponseEntity patchIngredient(@PathVariable("ingredient-id") long ingredientId, @RequestBody IngredientDto.Patch ingredientPatchDto) {
        ingredientPatchDto.setIngredientId(ingredientId);
        Ingredient ingredient = mapper.ingredientPatchDtoToIngredient(ingredientPatchDto);
        Ingredient updatedIngredient = ingredientService.updateIngredient(ingredient);
        IngredientDto.Response response = mapper.ingredientToIngredientResponseDto(updatedIngredient);
        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Operation(summary = "재료 단일 조회", description = "특정 재료의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "재료 단일 조회 완료"),
            @ApiResponse(responseCode = "404", description = "재료를 찾을 수 없음")
    })
    @GetMapping("/{ingredient-id}")
    public ResponseEntity getIngredient(@PathVariable("ingredient-id") long ingredientId) {
        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        IngredientDto.Response response = mapper.ingredientToIngredientResponseDto(ingredient);
        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Operation(summary = "재료 전체 조회", description = "모든 재료를 페이지네이션과 함께 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "재료 전체 조회 완료"),
            @ApiResponse(responseCode = "400", description = "요청 파라미터 Validation 실패")
    })
    @GetMapping
    public ResponseEntity getIngredients(
            @Parameter(description = "페이지 번호 (1부터 시작), 누락시 기본값 1로 됨", example = "1")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "페이지당 데이터 개수, 누락시 기본값 10으로 됨", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "재료 타입 (MAIN, SEASONING), 누락시 타입 가리지 않고 전체 조회", example = "MAIN")
            @RequestParam(required = false) String dtype
    ) {
        Page<Ingredient> pageIngredients = ingredientService.findIngredients(page - 1, size, dtype);
        List<Ingredient> ingredients = pageIngredients.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.ingredientsToIngredientResponseDtos(ingredients), pageIngredients),
                HttpStatus.OK
        );
    }

    @Operation(summary = "재료 삭제", description = "특정 재료를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "재료 삭제 완료"),
            @ApiResponse(responseCode = "404", description = "재료를 찾을 수 없음")
    })
    @DeleteMapping("/{ingredient-id}")
    public ResponseEntity deleteIngredient(@PathVariable("ingredient-id") long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return ResponseEntity.noContent().build();
    }

}