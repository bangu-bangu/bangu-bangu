package com.github.bbooong.bangumall.payment.application;

import com.github.bbooong.bangumall.payment.application.dto.PaymentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public long requestPayment(final PaymentCreateRequest request) {
        return 1;
    }
}
