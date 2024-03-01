package com.github.bbooong.bangumall.order.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, Long> {

    Order save(Order order);

    Optional<Order> findByIdAndMemberId(long id, long memberId);
}
