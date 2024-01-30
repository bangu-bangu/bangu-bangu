package com.github.bbooong.bangumall.stock.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface StockRepository extends Repository<Stock, Long> {

    Optional<Stock> findById(long id);

    Stock save(Stock stock);

    List<Stock> findAllByProductId(long productId);
}
