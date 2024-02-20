package com.github.bbooong.bangumall.stock.domain;

import com.github.bbooong.bangumall.core.exception.BanguMallNullPointerException;
import com.github.bbooong.bangumall.stock.exception.StockDifferentProductException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNegativeException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import java.util.Comparator;
import java.util.List;

public class Stocks {

    private final List<Stock> stocks;

    private Stocks(final List<Stock> stocks) {
        if (stocks == null) {
            throw new BanguMallNullPointerException();
        }
        if (stocks.stream().map(Stock::getProductId).distinct().count() > 1) {
            throw new StockDifferentProductException();
        }

        this.stocks = stocks.stream().sorted(Comparator.comparing(Stock::getExpiredDate)).toList();
    }

    public static Stocks create(final List<Stock> stocks) {
        return new Stocks(stocks);
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
