package com.github.bbooong.bangumall.payment.domain;

public interface BanguPaymentClient {

    boolean requestPayment(final long orderId);
}
