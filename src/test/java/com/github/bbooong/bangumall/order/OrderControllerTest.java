package com.github.bbooong.bangumall.order;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;
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

    String sellerToken, customerToken;
    long 양념게장_id, 양배추_파스타_id;

    @BeforeEach
    public void init() {
        MemberFixture.createMember("seller@email.com", "seller");
        sellerToken = AuthFixture.login("seller@email.com", "seller");
        양념게장_id = ProductFixture.create("양념게장 1kg", 30000, "진짜 맛있음");
        양배추_파스타_id = ProductFixture.create("양배추 파스타", 18000, "소화가 잘되고 감칠맛이 나는 파스타");
        StockFixture.create(양념게장_id, 100, LocalDate.of(2034, 1, 30));
        StockFixture.create(양배추_파스타_id, 50, LocalDate.of(2034, 2, 1));

        MemberFixture.createMember("customer@email.com", "customer");
        customerToken = AuthFixture.login("customer@email.com", "customer");
    }

    @Nested
    @DisplayName("order를 생성할 때")
    class Describe_CreateOrder {

        @Nested
        @DisplayName("order 정보를 바르게 입력하면")
        class Context_With_ValidRequest {

            int 양념게장_수량 = 20;
            int 양배추_파스타_수량 = 13;

            @Test
            @DisplayName("order id를 반환한다.")
            void it_returns_orderId() {
                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
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
                                        .formatted(양념게장_id, 양념게장_수량, 양배추_파스타_id, 양배추_파스타_수량))
                        .when()
                        .post("/orders")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(LOCATION, matchesRegex("/orders/[0-9]+"));
            }
        }
    }
}
