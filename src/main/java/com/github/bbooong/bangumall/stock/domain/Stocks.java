package com.github.bbooong.bangumall.stock.domain;

import java.util.List;
import lombok.NonNull;

public class Stocks {

    private final List<Stock> stocks;

    public Stocks(@NonNull final List<Stock> stocks) {
        this.stocks = stocks;
    }

    public void decreaseQuantity(final int quantity) {}
}
