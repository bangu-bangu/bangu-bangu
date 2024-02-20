package com.github.bbooong.bangumall.stock.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Quantity {

    private int value;

    private Quantity(final int value) {
        this.value = value;
    }

    public static Quantity create(final int value) {
        return new Quantity(value);
    }
}
