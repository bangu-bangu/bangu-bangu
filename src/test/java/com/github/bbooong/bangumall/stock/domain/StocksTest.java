package com.github.bbooong.bangumall.stock.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import com.github.bbooong.bangumall.stock.exception.StockQuantityNegativeException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("단위 테스트: Stocks")
class StocksTest {

    @Nested
    @DisplayName("Stocks를 생성할 때")
    class Describe_NewStocks {

        @Nested
        @DisplayName("null을 전달하면")
        class Context_With_NullList {

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                assertThatCode(() -> new Stocks(null))
                        .isExactlyInstanceOf(NullPointerException.class);
            }
        }
    }

    @Nested
    @DisplayName("decreaseQuantity()를 호출할 때")
    class Describe_DecreaseQuantity {

        @Nested
        @DisplayName("0보다 작은 값을 전달하면")
        class Context_With_NegativeQuantity {

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                final Stocks stocks = new Stocks(List.of());

                assertThatCode(() -> stocks.decreaseQuantity(-1))
                        .isExactlyInstanceOf(StockQuantityNegativeException.class);
            }
        }
    }
}
