package com.springboot.payment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PaymentMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMasterId;

    @Column(nullable = false)
    private int amount; // 결제 금액

    @Column(nullable = false)
    private int riceAmount; // 밥풀 수

    @Column(nullable = false)
    private String image; // 이미지 URL

    @Column(nullable = false)
    private String name; // 상품 이름 (ex. 한 톨, 한 숟가락 등)

}
