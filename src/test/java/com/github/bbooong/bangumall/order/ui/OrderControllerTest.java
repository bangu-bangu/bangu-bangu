package com.github.bbooong.bangumall.order.ui;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;
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
import org.springframework.http.HttpStatus;

@DisplayName("인수 테스트: Order")
@AcceptanceTest
class OrderControllerTest {

    String 판매자_token, 구매자_token;
    long 양념게장_id, 양배추_파스타_id;

    @BeforeEach
    public void init() {
        MemberFixture.createMember("vendor@email.com", "vendor", "VENDOR");
        판매자_token = AuthFixture.login("vendor@email.com", "vendor");
        양념게장_id = ProductFixture.create(판매자_token, "양념게장 1kg", 30000, "진짜 맛있음");
        양배추_파스타_id = ProductFixture.create(판매자_token, "양배추 파스타", 18000, "소화가 잘되고 감칠맛이 나는 파스타");
        StockFixture.create(판매자_token, 양념게장_id, 100, LocalDate.of(2034, 1, 30));
        StockFixture.create(판매자_token, 양배추_파스타_id, 50, LocalDate.of(2034, 2, 1));

        MemberFixture.createMember("customer@email.com", "customer", "CUSTOMER");
        구매자_token = AuthFixture.login("customer@email.com", "customer");
    }

    @Nested
    @DisplayName("order를 생성할 때")
    class Describe_CreateOrder {

        @Nested
        @DisplayName("order 정보를 바르게 입력하면")
        class Context_With_ValidRequest {

            int 양념게장_재고, 양배추_파스타_재고;
            int 양념게장_주문_수량 = 20;
            int 양배추_파스타_주문_수량 = 13;

            @BeforeEach
            void setUp() {
                양념게장_재고 =
                        RestAssured.given()
                                .contentType(APPLICATION_JSON_VALUE)
                                .auth()
                                .oauth2(판매자_token)
                                .when()
                                .get("/products/{productId}/stocks", 양념게장_id)
                                .then()
                                .statusCode(OK.value())
                                .extract()
                                .body()
                                .jsonPath()
                                .getInt("[0].quantity");
                양배추_파스타_재고 =
                        RestAssured.given()
                                .contentType(APPLICATION_JSON_VALUE)
                                .auth()
                                .oauth2(판매자_token)
                                .when()
                                .get("/products/{productId}/stocks", 양배추_파스타_id)
                                .then()
                                .statusCode(OK.value())
                                .extract()
                                .body()
                                .jsonPath()
                                .getInt("[0].quantity");
            }

            @Test
            @DisplayName("order id를 반환하고 재고를 차감한다.")
            void it_returns_orderId_and_consume_stocks() {
                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
                        .auth()
                        .oauth2(구매자_token)
                        .body(
                                """
                                        {
                                            "orderLines": [
                                                {
                                                    "productId": %d,
                                                    "quantity": %d
                                                },
                                                {
                                                    "productId": %d,
                                                    "quantity": %d
                                                }
                                            ]
                                        }
                                        """
                                        .formatted(양념게장_id, 양념게장_주문_수량, 양배추_파스타_id, 양배추_파스타_주문_수량))
                        .when()
                        .post("/orders")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(LOCATION, matchesRegex("/orders/[0-9]+"));

                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
                        .auth()
                        .oauth2(판매자_token)
                        .when()
                        .get("/products/{productId}/stocks", 양념게장_id)
                        .then()
                        .statusCode(OK.value())
                        .body("[0].quantity", is(양념게장_재고 - 양념게장_주문_수량))
                        .body("[0].expiredDate", is("2034-01-30"));

                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
                        .auth()
                        .oauth2(판매자_token)
                        .when()
                        .get("/products/{productId}/stocks", 양배추_파스타_id)
                        .then()
                        .statusCode(OK.value())
                        .body("[0].quantity", is(양배추_파스타_재고 - 양배추_파스타_주문_수량))
                        .body("[0].expiredDate", is("2034-02-01"));
            }
        }
    }
}
