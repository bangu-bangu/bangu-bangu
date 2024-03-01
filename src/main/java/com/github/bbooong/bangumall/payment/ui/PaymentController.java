package com.github.bbooong.bangumall.payment.ui;

import com.github.bbooong.bangumall.auth.domain.Authorities;
import com.github.bbooong.bangumall.auth.domain.MemberRole;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    // 결제 요청
    @PostMapping
    @Authorities(MemberRole.CUSTOMER)
    public ResponseEntity<Void> requestPayment() {
        return ResponseEntity.created(URI.create("/payments/" + 1)).build();
    }

    // 결제 확인

    // 결제 취소
}
