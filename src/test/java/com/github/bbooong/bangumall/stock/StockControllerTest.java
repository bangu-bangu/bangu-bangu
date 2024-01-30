package com.github.bbooong.bangumall.stock;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.bbooong.bangumall.config.AcceptanceTest;
import com.github.bbooong.bangumall.fixture.AuthFixture;
import com.github.bbooong.bangumall.fixture.MemberFixture;
import com.github.bbooong.bangumall.fixture.ProductFixture;
import com.github.bbooong.bangumall.fixture.StockFixture;
import io.restassured.RestAssured;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@DisplayName("인수 테스트: Stock")
@AcceptanceTest
class StockControllerTest {

    String sellerToken;
    long 양념게장_id;

    @BeforeEach
    public void init() {
        final String email = "test@email.com";
        final String password = "test";

        MemberFixture.createMember(email, password);
        sellerToken = AuthFixture.login(email, password);
        양념게장_id = ProductFixture.create("양념게장 1kg", 30000, "진짜 맛있음");
    }

    @Nested
    @DisplayName("stock을 생성할 때")
    class Describe_CreateStock {

        @Nested
        @DisplayName("stock 정보를 바르게 입력하면")
        class Context_With_ValidRequest {

            final int quantity = 100;
            final LocalDate expiredDate = LocalDate.of(2034, 1, 30);

            @Test
            @DisplayName("stock id를 반환한다.")
            void it_returns_stockId() {
                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
                        .body(
                                """
                                        {
                                            "quantity": %s,
                                            "expiredDate": "%s"
                                        }
                                        """
                                        .formatted(quantity, expiredDate))
                        .when()
                        .post("/products/{productId}/stocks", 양념게장_id)
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(
                                HttpHeaders.LOCATION,
                                matchesRegex("/products/[0-9]+/stocks/[0-9]+"));
            }
        }
    }

    @Nested
    @DisplayName("stock을 수정할 때")
    class Describe_ModifyStock {

        long 양념게장_100개_id;

        @BeforeEach
        public void init() {
            양념게장_100개_id = StockFixture.create(양념게장_id, 100, LocalDate.of(2034, 1, 30));
        }

        @Nested
        @DisplayName("stock 정보를 바르게 입력하면")
        class Context_With_ValidRequest {

            final int quantity = 200;
            final LocalDate expiredDate = LocalDate.of(2034, 1, 30);

            @Test
            @DisplayName("수정된 stock 정보를 반환한다.")
            void it_returns_updatedStockInformation() {
                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
                        .body(
                                """
                                        {
                                            "quantity": %s,
                                            "expiredDate": "%s"
                                        }
                                        """
                                        .formatted(quantity, expiredDate))
                        .when()
                        .put("/stocks/{id}", 양념게장_100개_id)
                        .then()
                        .statusCode(OK.value())
                        .body("quantity", is(quantity))
                        .body("expiredDate", is(expiredDate.toString()));
            }
        }
    }
}
