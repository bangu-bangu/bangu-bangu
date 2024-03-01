package com.github.bbooong.bangumall.payment.infrastructure;

import com.github.bbooong.bangumall.payment.domain.BanguPaymentClient;
import org.springframework.stereotype.Component;

@Component
public class BanguPaymentClientImpl implements BanguPaymentClient {

    @Override
    public void requestPayment(final long orderId) { // TODO: 구현
    }
}
