package com.github.bbooong.bangumall.payment.domain;

public interface BanguPaymentClient {

    void requestPayment(final long orderId);
}
