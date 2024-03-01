package com.github.bbooong.bangumall.payment.infrastructure;

import com.github.bbooong.bangumall.payment.domain.BanguPaymentClient;
import org.springframework.stereotype.Component;

@Component
public class BanguPaymentClientImpl implements BanguPaymentClient {

    @Override
    public boolean requestPayment(final long orderId) {
        return true;
    }
}
