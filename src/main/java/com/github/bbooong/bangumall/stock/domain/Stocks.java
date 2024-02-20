package com.github.bbooong.bangumall.stock.domain;

import com.github.bbooong.bangumall.stock.exception.StockDifferentProductException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNegativeException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import java.util.List;
import lombok.NonNull;

public class Stocks {

    private final List<Stock> stocks;

    public Stocks(@NonNull final List<Stock> stocks) {
        if (stocks.stream().map(Stock::getProductId).distinct().count() > 1) {
            throw new StockDifferentProductException();
        }
        this.stocks = stocks;
    }

    public void decreaseQuantity(int quantity) {
        // TODO: quantity VO 추가
        if (quantity < 0) {
            throw new StockQuantityNegativeException();
        }

        if (quantity > stocks.stream().mapToInt(Stock::getQuantity).sum()) {
            throw new StockQuantityNotEnoughException();
        }

        for (final Stock stock : stocks) {
            if (quantity <= 0) {
                break;
            }

            final int decreaseQuantity = Math.min(stock.getQuantity(), quantity);
            stock.decreaseQuantity(decreaseQuantity);
            quantity -= decreaseQuantity;
        }
    }
}
