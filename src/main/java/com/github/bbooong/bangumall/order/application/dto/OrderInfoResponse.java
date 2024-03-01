package com.github.bbooong.bangumall.order.application.dto;

import java.util.List;

public record OrderInfoResponse(List<OrderLineInfoResponse> orderLines, long totalPrice) {

    public record OrderLineInfoResponse(long productId, int price, int quantity) {}
}
