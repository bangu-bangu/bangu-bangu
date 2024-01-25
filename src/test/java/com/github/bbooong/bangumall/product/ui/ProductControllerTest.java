package com.github.bbooong.bangumall.product.ui;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;

import com.github.bbooong.bangumall.config.AcceptanceTest;
import com.github.bbooong.bangumall.fixture.AuthFixture;
import com.github.bbooong.bangumall.fixture.MemberFixture;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("인수 테스트: Product")
@AcceptanceTest
class ProductControllerTest {

    @Nested
    @DisplayName("product를 생성할 때")
    class Describe_CreateProduct {

        String sellerToken;

        @BeforeEach
        public void init() {
            final String email = "test@email.com";
            final String password = "test";

            MemberFixture.createMember(email, password);
            sellerToken = AuthFixture.login(email, password);
        }

        @Nested
        @DisplayName("product 정보를 바르게 입력하면")
        class Context_With_ValidRequest {

            final String name = "양념게장 1kg";
            final int price = 30000;

            @Test
            @DisplayName("product id를 반환한다.")
            void it_returns_productId() {
                RestAssured.given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(
                                """
                    {
                        "name": "%s",
                        "price": "%s"
                    }
                    """
                                        .formatted(name, price))
                        .when()
                        .post("/products")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(LOCATION, matchesRegex("/products/[0-9]+"));
            }
        }
    }
}
