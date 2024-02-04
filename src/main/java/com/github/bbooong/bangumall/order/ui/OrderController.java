package com.github.bbooong.bangumall.order.ui;

import com.github.bbooong.bangumall.order.application.OrderService;
import com.github.bbooong.bangumall.order.application.dto.OrderCreateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody final OrderCreateRequest request) {
        final long orderId = orderService.createOrder(1L, request);

        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }
}
