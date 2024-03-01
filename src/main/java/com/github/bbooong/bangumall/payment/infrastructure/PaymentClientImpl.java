package com.github.bbooong.bangumall.payment.infrastructure;

import com.github.bbooong.bangumall.payment.domain.PaymentClient;
import org.springframework.stereotype.Component;

@Component
public class PaymentClientImpl implements PaymentClient {

    @Override
    public boolean requestPayment(final long orderId) {
        return true;
    }
}
