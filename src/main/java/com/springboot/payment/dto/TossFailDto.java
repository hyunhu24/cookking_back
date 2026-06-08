package com.springboot.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TossFailDto {
    private String orderId;
    private String paymentKey;
    private String message;  // 실패 사유
}