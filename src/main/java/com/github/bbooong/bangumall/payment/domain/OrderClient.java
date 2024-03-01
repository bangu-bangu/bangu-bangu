package com.github.bbooong.bangumall.payment.domain;

public interface OrderClient {

    OrderInfo getOrder(final long memberId, final long orderId);
}
