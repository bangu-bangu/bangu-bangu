package com.github.bbooong.bangumall.stock.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

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
}
