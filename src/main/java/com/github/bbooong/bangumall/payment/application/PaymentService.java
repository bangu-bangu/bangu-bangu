package com.github.bbooong.bangumall.payment.application;

import com.github.bbooong.bangumall.payment.application.dto.PaymentCreateRequest;
import com.github.bbooong.bangumall.payment.domain.OrderClient;
import com.github.bbooong.bangumall.payment.domain.OrderInfo;
import com.github.bbooong.bangumall.payment.domain.Payment;
import com.github.bbooong.bangumall.payment.domain.PaymentClient;
import com.github.bbooong.bangumall.payment.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentClient paymentClient;
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    @Transactional
    public long requestPayment(final long memberId, final PaymentCreateRequest request) {
        // TODO: Order 상태 검증 추가
        final OrderInfo orderInfo = orderClient.getOrder(memberId, request.orderId());

        paymentClient.requestPayment(request.orderId()); // TODO: 실패 시 처리
        final Payment payment = Payment.create(request.orderId(), orderInfo.totalPrice());
        // TODO: Order 상태 변경
        return paymentRepository.save(payment).getId();
    }
}
