package com.github.bbooong.bangumall.payment.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface PaymentRepository extends Repository<Payment, Long> {

    Payment save(Payment payment);

    Optional<Payment> findById(long id);

    void delete(Payment payment);
}
