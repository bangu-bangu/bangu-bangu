package com.github.bbooong.bangumall.stock.domain;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.Repository;

public interface StockRepository extends Repository<Stock, Long> {

    Optional<Stock> findById(long id);

    Stock save(Stock stock);

    List<Stock> findAllByProductId(long productId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Stock> findAllExclusivelyByProductId(long productId);
}
