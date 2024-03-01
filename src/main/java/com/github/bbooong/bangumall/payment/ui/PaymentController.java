package com.github.bbooong.bangumall.payment.ui;

import com.github.bbooong.bangumall.auth.application.dto.AuthPrincipal;
import com.github.bbooong.bangumall.auth.domain.Authenticated;
import com.github.bbooong.bangumall.auth.domain.Authorities;
import com.github.bbooong.bangumall.auth.domain.MemberRole;
import com.github.bbooong.bangumall.payment.application.PaymentService;
import com.github.bbooong.bangumall.payment.application.dto.PaymentCreateRequest;
import com.github.bbooong.bangumall.payment.application.dto.PaymentInfoResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 결제 요청
    @PostMapping
    @Authorities(MemberRole.CUSTOMER)
    public ResponseEntity<Void> requestPayment(
            @Authenticated final AuthPrincipal authPrincipal,
            @RequestBody @Valid final PaymentCreateRequest request) {
        final long paymentId = paymentService.requestPayment(authPrincipal.memberId(), request);

        return ResponseEntity.created(URI.create("/payments/" + paymentId)).build();
    }

    // 결제 확인
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Authorities(MemberRole.CUSTOMER)
    public PaymentInfoResponse getPayment(
            @Authenticated final AuthPrincipal authPrincipal, @PathVariable final long id) {
        return paymentService.getPayment(authPrincipal.memberId(), id);
    }

    // 결제 취소
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Authorities(MemberRole.CUSTOMER)
    public void cancelPayment(
            @Authenticated final AuthPrincipal authPrincipal, @PathVariable final long id) {
        paymentService.cancelPayment(authPrincipal.memberId(), id);
    }
}
