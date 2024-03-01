package com.github.bbooong.bangumall.payment.domain;

import org.springframework.data.repository.Repository;

public interface PaymentRepository extends Repository<Payment, Long> {

    Payment save(Payment payment);
}
