package com.github.bbooong.bangumall.order.ui;

import com.github.bbooong.bangumall.auth.domain.Authorities;
import com.github.bbooong.bangumall.auth.domain.MemberRole;
import com.github.bbooong.bangumall.order.application.OrderService;
import com.github.bbooong.bangumall.order.application.dto.OrderCreateRequest;
import com.github.bbooong.bangumall.order.application.dto.OrderInfoResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Authorities(MemberRole.CUSTOMER)
    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody final OrderCreateRequest request) {
        final long orderId =
                orderService.createOrder(1L, request); // TODO: 인증, 인가 구현 후 memberId를 사용하도록 변경

        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @Authorities(MemberRole.CUSTOMER)
    public OrderInfoResponse getOrder(@PathVariable final long orderId) {
        return new OrderInfoResponse(null, 0L); // TODO: 구현 필요
    }
}
