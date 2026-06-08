package com.springboot.payment.service;

import com.springboot.payment.entity.PaymentMaster;
import com.springboot.payment.repository.PaymentMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMasterService {

    private final PaymentMasterRepository paymentMasterRepository;

    public List<PaymentMaster> findAllMasters() {
        return paymentMasterRepository.findAll();
    }
}
