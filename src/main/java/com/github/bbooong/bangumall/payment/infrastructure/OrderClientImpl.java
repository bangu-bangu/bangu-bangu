package com.github.bbooong.bangumall.payment.infrastructure;

import com.github.bbooong.bangumall.auth.application.dto.AuthPrincipal;
import com.github.bbooong.bangumall.order.application.dto.OrderInfoResponse;
import com.github.bbooong.bangumall.order.ui.OrderController;
import com.github.bbooong.bangumall.payment.domain.OrderClient;
import com.github.bbooong.bangumall.payment.domain.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderClientImpl implements OrderClient {

    private final OrderController orderController;

    @Override
    public OrderInfo getOrder(final long memberId, final long orderId) {
        final OrderInfoResponse response =
                orderController.getOrder(new AuthPrincipal(memberId), orderId);
        return new OrderInfo(response.totalPrice());
    }
}
