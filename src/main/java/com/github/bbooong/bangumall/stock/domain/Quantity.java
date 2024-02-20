package com.github.bbooong.bangumall.stock.domain;

import com.github.bbooong.bangumall.stock.exception.StockQuantityNegativeException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import jakarta.persistence.Embeddable;
import java.util.Collection;
import java.util.function.Function;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Quantity {

    public static final Quantity ZERO = new Quantity(0);

    private int value;

    private Quantity(final int value) {
        if (value < 0) {
            throw new StockQuantityNegativeException();
        }
        this.value = value;
    }

    public static Quantity create(final int value) {
        return new Quantity(value);
    }

    public static <T> Quantity sum(
            final Collection<T> values, final Function<T, Quantity> monetary) {
        return values.stream().map(monetary).reduce(Quantity.ZERO, Quantity::add);
    }

    public Quantity subtract(final int value) {
        if (this.value < value) {
            throw new StockQuantityNotEnoughException();
        }
        return new Quantity(this.value - value);
    }

    public Quantity add(final int value) {
        return new Quantity(this.value + value);
    }

    public boolean isLessThan(final int value) {
        return this.value < value;
    }
}
