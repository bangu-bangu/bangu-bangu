package com.github.bbooong.bangumall.payment.ui;

import com.github.bbooong.bangumall.config.AcceptanceTest;
import com.github.bbooong.bangumall.fixture.AuthFixture;
import com.github.bbooong.bangumall.fixture.MemberFixture;
import com.github.bbooong.bangumall.fixture.OrderFixture;
import com.github.bbooong.bangumall.fixture.ProductFixture;
import com.github.bbooong.bangumall.fixture.StockFixture;
import io.restassured.RestAssured;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("인수 테스트: Payment")
@AcceptanceTest
class PaymentControllerTest {

    String 구매자_token, 판매자_token;
    long 양념게장_id, 양배추_파스타_id;

    @BeforeEach
    void setUp() {
        MemberFixture.createMember("customer@email.com", "customer", "CUSTOMER");
        구매자_token = AuthFixture.login("customer@email.com", "customer");

        MemberFixture.createMember("vendor@email.com", "vendor", "VENDOR");
        판매자_token = AuthFixture.login("vendor@email.com", "vendor");
        양념게장_id = ProductFixture.create(판매자_token, "양념게장 1kg", 30000, "진짜 맛있음");
        양배추_파스타_id = ProductFixture.create(판매자_token, "양배추 파스타", 18000, "소화가 잘되고 감칠맛이 나는 파스타");
        StockFixture.create(판매자_token, 양념게장_id, 100, LocalDate.of(2034, 1, 30));
        StockFixture.create(판매자_token, 양배추_파스타_id, 100, LocalDate.of(2034, 2, 1));
    }

    @Nested
    @DisplayName("결제를 요청할 때")
    class Describe_RequestPayment {

        @Nested
        @DisplayName("구매자가 결제되지 않은 주문을 요청하면")
        class Context_With_CustomerRequestPaymentNotPaid {

            long 결제대기_주문_id;

            @BeforeEach
            void setUp() {
                final Map<String, Object> 양념게장_주문 = Map.of("productId", 양념게장_id, "quantity", 5);
                final Map<String, Object> 양배추_파스타_주문 =
                        Map.of("productId", 양배추_파스타_id, "quantity", 5);
                결제대기_주문_id = OrderFixture.order(구매자_token, List.of(양념게장_주문, 양배추_파스타_주문));
            }

            @Test
            @DisplayName("결제가 완료되어야 한다")
            void it_completes_payment() {
                RestAssured.given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .auth()
                        .oauth2(구매자_token)
                        .body(
                                """
                        {
                            "orderId": %d
                        }
                        """
                                        .formatted(결제대기_주문_id))
                        .when()
                        .post("/payments")
                        .then()
                        .statusCode(HttpStatus.CREATED.value());
            }
        }
    }
}
