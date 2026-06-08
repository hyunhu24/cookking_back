package com.springboot.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDto {
    @Schema(description = "결제 금액", example = "10000")
    private int amount;         // 결제 금액
    @Schema(description = "밥풀 양", example = "100")
    private int riceAmount;     // 밥풀 양
}

