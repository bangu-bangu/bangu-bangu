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

    public static Quantity min(final Quantity q1, final Quantity q2) {
        return q1.isLessThan(q2) ? q1 : q2;
    }

    public Quantity subtract(final Quantity other) {
        if (isLessThan(other)) {
            throw new StockQuantityNotEnoughException();
        }
        return new Quantity(this.value - other.value);
    }

    public Quantity add(final Quantity other) {
        return new Quantity(this.value + other.value);
    }

    public boolean isLessThan(final Quantity other) {
        return this.value < other.value;
    }
}
