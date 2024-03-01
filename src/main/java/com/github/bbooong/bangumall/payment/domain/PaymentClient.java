package com.github.bbooong.bangumall.payment.domain;

public interface PaymentClient {

    boolean requestPayment(final long orderId);
}
