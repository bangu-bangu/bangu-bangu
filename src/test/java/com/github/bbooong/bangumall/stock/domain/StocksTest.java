package com.github.bbooong.bangumall.stock.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.bbooong.bangumall.stock.exception.StockQuantityNegativeException;
import com.github.bbooong.bangumall.stock.exception.StockQuantityNotEnoughException;
import java.time.LocalDate;
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

        @Nested
        @DisplayName("전체 수량보다 더 많은 값으로 요청하면")
        class Context_With_QuantityMoreThanTotalQuantity {

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                final Stocks stocks = new Stocks(List.of());

                assertThatCode(() -> stocks.decreaseQuantity(1))
                        .isExactlyInstanceOf(StockQuantityNotEnoughException.class);
            }
        }

        @Nested
        @DisplayName("전체 수량보다 같거나 적은 값으로 요청하면")
        class Context_With_QuantityLessThanOrEqualToTotalQuantity {

            @Test
            @DisplayName("유통기한 순으로 재고를 감소한다.")
            void it_decreases_quantity() {
                final Stock olderStock = Stock.create(1, 10, LocalDate.now().plusDays(1));
                final Stock newerStock = Stock.create(1, 20, LocalDate.now().plusDays(2));
                final Stocks stocks = new Stocks(List.of(olderStock, newerStock));

                stocks.decreaseQuantity(15);
                assertAll(
                        () -> assertThat(olderStock.getQuantity()).isEqualTo(0),
                        () -> assertThat(newerStock.getQuantity()).isEqualTo(15));
            }
        }
    }
}
