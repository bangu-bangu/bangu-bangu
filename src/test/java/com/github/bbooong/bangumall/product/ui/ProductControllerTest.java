package com.github.bbooong.bangumall.product.ui;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;

import com.github.bbooong.bangumall.config.AcceptanceTest;
import com.github.bbooong.bangumall.fixture.AuthFixture;
import com.github.bbooong.bangumall.fixture.MemberFixture;
import com.github.bbooong.bangumall.fixture.ProductFixture;
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
            final String description = "진짜 맛있음";

            @Test
            @DisplayName("product id를 반환한다.")
            void it_returns_productId() {
                RestAssured.given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(
                                """
                                        {
                                            "name": "%s",
                                            "price": "%s",
                                            "description": "%s"
                                        }
                                        """
                                        .formatted(name, price, description))
                        .when()
                        .post("/products")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(LOCATION, matchesRegex("/products/[0-9]+"));
            }
        }
    }

    @Nested
    @DisplayName("전체 product를 조회할 때")
    class Describe_GetProducts {

        private Long 양배추_파스타_id;
        private Long 파인애플_스무디_id;
        private Long 에이스_씬에스프레소_id;

        @BeforeEach
        public void init() {
            양배추_파스타_id = ProductFixture.create("양배추 파스타", 18000, "JMT");
            파인애플_스무디_id = ProductFixture.create("파인애플 스무디", 7000, "GJMT");
            에이스_씬에스프레소_id = ProductFixture.create("에이스 씬에스프레소", 3000, "NMT");
        }

        @Test
        @DisplayName("전체 product의 정보를 반환한다.")
        void it_returns_productsInfo() {
            RestAssured.given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/products")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("size()", is(3))
                    .body("[0].id", is(양배추_파스타_id.intValue()))
                    .body("[0].name", is("양배추 파스타"))
                    .body("[0].price", is(18000))
                    .body("[1].id", is(파인애플_스무디_id.intValue()))
                    .body("[1].name", is("파인애플 스무디"))
                    .body("[1].price", is(7000))
                    .body("[2].id", is(에이스_씬에스프레소_id.intValue()))
                    .body("[2].name", is("에이스 씬에스프레소"))
                    .body("[2].price", is(3000));
        }
    }

    @Nested
    @DisplayName("product를 조회할 때")
    class Describe_GetProduct {

        private Long 양배추_파스타_id;

        @BeforeEach
        public void init() {
            양배추_파스타_id = ProductFixture.create("양배추 파스타", 18000, "소화가 잘되고 감칠맛이 나는 파스타");
        }

        @Nested
        @DisplayName("product id를 바르게 입력하면")
        class Context_With_ValidProductId {

            @Test
            @DisplayName("product 상세 정보를 반환한다.")
            void it_returns_productDetail() {
                RestAssured.given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when()
                        .get("/products/{id}", 양배추_파스타_id)
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("id", is(양배추_파스타_id.intValue()))
                        .body("name", is("양배추 파스타"))
                        .body("price", is(18000))
                        .body("description", is("소화가 잘되고 감칠맛이 나는 파스타"));
            }
        }
    }
}
