package com.springboot.payment.dto;

import com.springboot.payment.entity.Payment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PaymentHistoryDto {
    private String orderId;
    private int amount;
    private int riceAmount;
    private Payment.PaymentStatus paymentStatus;
    private String riceImage;
    private String riceName;
    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;
}