package com.springboot.payment.repository;

import com.springboot.payment.entity.PaymentMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMasterRepository extends JpaRepository<PaymentMaster, Long> {
    Optional<PaymentMaster> findByRiceAmount(int riceAmount);
}

