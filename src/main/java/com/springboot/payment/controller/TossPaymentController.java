package com.springboot.payment.controller;

import com.springboot.config.TossPaymentConfig;
import com.springboot.member.entity.Member;
import com.springboot.payment.dto.*;
import com.springboot.payment.entity.Payment;
import com.springboot.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "토스 결제", description = "토스페이먼츠 결제 API")
@RestController
@RequestMapping("/api/v1/payments/toss")
public class TossPaymentController {

    private final PaymentService paymentService;
    private final TossPaymentConfig tossPaymentConfig;

    public TossPaymentController(PaymentService paymentService, TossPaymentConfig tossPaymentConfig) {
        this.paymentService = paymentService;
        this.tossPaymentConfig = tossPaymentConfig;
    }

    @Operation(summary = "토스 결제 요청", description = "결제 요청을 생성하고 결제창에 필요한 정보를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결제 요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/request")
    public ResponseEntity requestTossPayment(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                             @RequestBody @Valid PaymentRequestDto dto) {
        Payment payment = paymentService.requestTossPayment(dto, member.getMemberId());

        PaymentResponseDto responseDto = PaymentResponseDto.builder()
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .clientKey(tossPaymentConfig.getTestClientApiKey())
                .orderName("밥풀 " + payment.getRiceAmount() + "개 충전")
                .successUrl(tossPaymentConfig.getSuccessUrl())
                .failUrl(tossPaymentConfig.getFailUrl())
                .build();

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "토스 결제 승인", description = "결제 성공 시 토스 API로 결제를 승인하고, 결제 상태를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결제 승인 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/confirm")
    public ResponseEntity confirmPayment(@RequestBody TossSuccessDto dto) {
        paymentService.confirmPayment(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fail")
    @Operation(summary = "토스 결제 실패 처리", description = "결제 실패 시 결제 상태를 실패로 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결제 실패 처리 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    public ResponseEntity tossPaymentFail(@RequestBody TossFailDto failDto) {
        paymentService.failPayment(failDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel")
    @Operation(summary = "토스 결제 취소 처리", description = "결제 취소 시 결제 상태를 취소로 업데이트하고 포인트를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결제 취소 처리 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    public ResponseEntity tossPaymentCancel(@RequestBody TossCancelDto cancelDto) {
        paymentService.cancelPayment(cancelDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history")
    @Operation(summary = "결제 내역 조회", description = "로그인한 회원의 모든 결제 내역을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결제 내역 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    public ResponseEntity getChargingHistory(@Parameter(hidden = true) @AuthenticationPrincipal Member member) {
        List<PaymentHistoryDto> historyDtos = paymentService.historyPayment(member.getMemberId());
        return ResponseEntity.ok(historyDtos);
    }
}