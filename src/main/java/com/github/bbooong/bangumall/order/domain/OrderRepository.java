package com.github.bbooong.bangumall.order.domain;

import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, Long> {

    Order save(Order order);
}