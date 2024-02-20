package com.github.bbooong.bangumall.stock.domain;

import com.github.bbooong.bangumall.core.exception.BanguMallNotAllowedNullException;
import com.github.bbooong.bangumall.stock.exception.StockDifferentProductException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import java.util.Comparator;
import java.util.List;

public class Stocks {

    private final List<Stock> values;

    private Stocks(final List<Stock> values) {
        if (values == null) {
            throw new BanguMallNotAllowedNullException();
        }
        if (values.stream().map(Stock::getProductId).distinct().count() > 1) {
            throw new StockDifferentProductException();
        }

        this.values = values.stream().sorted(Comparator.comparing(Stock::getExpiredDate)).toList();
    }

    public static Stocks create(final List<Stock> stocks) {
        return new Stocks(stocks);
    }

    public void decreaseQuantity(int quantity) {
        if (Quantity.sum(values, Stock::getQuantity).isLessThan(quantity)) {
            throw new StockQuantityNotEnoughException();
        }

        for (final Stock stock : values) {
            if (quantity <= 0) {
                break;
            }

            // TODO: 아래 로직 리팩토링
            final int decreaseQuantity = Math.min(stock.getQuantity().getValue(), quantity);
            stock.decreaseQuantity(decreaseQuantity);
            quantity -= decreaseQuantity;
        }
    }
}
