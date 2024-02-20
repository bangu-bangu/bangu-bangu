package com.github.bbooong.bangumall.stock.application.dto;

import com.github.bbooong.bangumall.stock.domain.Stock;
import java.time.LocalDate;

public record StockInfoResponse(long id, int quantity, LocalDate expiredDate) {

    public static StockInfoResponse from(final Stock stock) {
        return new StockInfoResponse(
                stock.getId(), stock.getQuantity().getValue(), stock.getExpiredDate());
    }
}
