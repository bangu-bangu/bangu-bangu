package com.github.bbooong.bangumall.stock.domain;

import com.github.bbooong.bangumall.stock.exception.StockQuantityNegativeException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import java.util.List;
import lombok.NonNull;

public class Stocks {

    private final List<Stock> stocks;

    public Stocks(@NonNull final List<Stock> stocks) {
        this.stocks = stocks;
    }

    public void decreaseQuantity(final int quantity) {
        // TODO: quantity VO 추가
        if (quantity < 0) {
            throw new StockQuantityNegativeException();
        }

        if (quantity > stocks.stream().mapToInt(Stock::getQuantity).sum()) {
            throw new StockQuantityNotEnoughException();
        }
    }
}
