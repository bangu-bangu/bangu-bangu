package com.github.bbooong.bangumall.order.application.dto;

import com.github.bbooong.bangumall.order.domain.Order;
import java.util.List;

public record OrderInfoResponse(List<OrderLineInfoResponse> orderLines, long totalPrice) {

    private record OrderLineInfoResponse(long productId, int price, int quantity) {}

    public static OrderInfoResponse create(final Order order) {
        return new OrderInfoResponse(
                order.getOrderLines().stream()
                        .map(
                                orderLine ->
                                        new OrderLineInfoResponse(
                                                orderLine.getProductId(),
                                                orderLine.getPrice(),
                                                orderLine.getQuantity()))
                        .toList(),
                order.totalPrice());
    }
}
