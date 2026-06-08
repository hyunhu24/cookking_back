package com.springboot.payment.repository;

import com.springboot.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderId(String orderId);
    Optional<Payment> findByPaymentKey(String paymentKey);
    List<Payment> findAllByMember_MemberIdOrderByRequestedAtDesc(Long memberId);
}

