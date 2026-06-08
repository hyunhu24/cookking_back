package com.springboot.payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDto {
    private String orderId;     // 주문 ID (UUID)
    private int amount;         // 결제 금액
    private String clientKey;   // 토스 클라이언트 키
    private String orderName;   // 예: "밥풀 100개 충전"
    private String successUrl;  // 성공 URL
    private String failUrl;     // 실패 URL
}

