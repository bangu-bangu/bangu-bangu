package com.github.bbooong.bangumall.order.ui;

import com.github.bbooong.bangumall.auth.application.dto.AuthPrincipal;
import com.github.bbooong.bangumall.auth.domain.Authenticated;
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
    public ResponseEntity<Void> createOrder(
            @Authenticated final AuthPrincipal authPrincipal,
            @Valid @RequestBody final OrderCreateRequest request) {
        final long orderId = orderService.createOrder(authPrincipal.memberId(), request);

        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Authorities(MemberRole.CUSTOMER)
    public OrderInfoResponse getOrder(
            @Authenticated final AuthPrincipal authPrincipal, @PathVariable final long id) {
        return orderService.getOrder(authPrincipal.memberId(), id);
    }
}
