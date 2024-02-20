package com.github.bbooong.bangumall.stock.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import com.github.bbooong.bangumall.stock.exception.StockQuantityNegativeException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("단위 테스트: Quantity")
class QuantityTest {

    @Nested
    @DisplayName("create() 를 호출할 때")
    class Describe_Create {

        @Nested
        @DisplayName("음수를 전달하면")
        class Context_With_NegativeValue {

            final int value = -1;

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                assertThatCode(() -> Quantity.create(value))
                        .isExactlyInstanceOf(StockQuantityNegativeException.class)
                        .hasMessage("재고 수량은 음수가 될 수 없습니다.");
            }
        }
    }

    @Nested
    @DisplayName("subtract() 를 호출할 때")
    class Describe_Subtract {

        @Nested
        @DisplayName("더 큰 수로 빼면")
        class Context_With_BiggerValue {

            final Quantity quantity = Quantity.create(1);
            final Quantity biggerQuantity = Quantity.create(2);

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                assertThatCode(() -> quantity.subtract(biggerQuantity))
                        .isExactlyInstanceOf(StockQuantityNotEnoughException.class)
                        .hasMessage("수량이 부족합니다.");
            }
        }
    }
}
