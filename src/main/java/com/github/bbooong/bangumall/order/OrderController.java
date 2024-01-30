package com.github.bbooong.bangumall.order;

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

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody final OrderCreateRequest request) {
        final long orderId = 777L;

        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }
}
