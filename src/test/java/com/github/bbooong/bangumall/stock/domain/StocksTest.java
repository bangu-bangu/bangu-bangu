package com.github.bbooong.bangumall.stock.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.bbooong.bangumall.core.exception.BanguMallNotAllowedNullException;
import com.github.bbooong.bangumall.stock.exception.StockDifferentProductException;
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
                assertThatCode(() -> Stocks.create(null))
                        .isExactlyInstanceOf(BanguMallNotAllowedNullException.class);
            }
        }

        @Nested
        @DisplayName("여러 물품의 재고를 전달하면")
        class Context_With_StocksWithMultipleProducts {

            final Stock stock1 = Stock.create(1, 10, LocalDate.now().plusDays(1));
            final Stock stock2 = Stock.create(2, 10, LocalDate.now().plusDays(1));

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                assertThatCode(() -> Stocks.create(List.of(stock1, stock2)))
                        .isExactlyInstanceOf(StockDifferentProductException.class)
                        .hasMessage("한 가지 상품의 재고만 불러와야 합니다.");
            }
        }

        @Nested
        @DisplayName("유통기한이 서로 다른 재고들을 전달하면")
        class Context_With_StocksWithDifferentExpiredDate {

            final Stock olderStock = Stock.create(1, 10, LocalDate.now().plusDays(1));
            final Stock newerStock = Stock.create(1, 10, LocalDate.now().plusDays(2));

            @Test
            @DisplayName("유통기한이 짧은 순으로 정렬한다.")
            void it_sorts_stocksAscending() {
                assertThat(Stocks.create(List.of(newerStock, olderStock)))
                        .extracting("values")
                        .asList()
                        .containsExactly(olderStock, newerStock);
            }
        }
    }

    @Nested
    @DisplayName("decreaseQuantity()를 호출할 때")
    class Describe_DecreaseQuantity {

        @Nested
        @DisplayName("전체 수량보다 같거나 적은 값으로 요청하면")
        class Context_With_QuantityLessThanOrEqualToTotalQuantity {

            final Stock olderStock = Stock.create(1, 10, LocalDate.now().plusDays(1));
            final Stock newerStock = Stock.create(1, 20, LocalDate.now().plusDays(2));

            final Stocks stocks = Stocks.create(List.of(olderStock, newerStock));

            @Test
            @DisplayName("유통기한 순으로 재고를 감소한다.")
            void it_decreases_quantity() {
                stocks.decreaseQuantity(15);
                assertAll(
                        () -> assertThat(olderStock.getQuantity().getValue()).isEqualTo(0),
                        () -> assertThat(newerStock.getQuantity().getValue()).isEqualTo(15));
            }
        }
    }
}
