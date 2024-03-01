package com.github.bbooong.bangumall.fixture;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;

import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public abstract class PaymentFixture {

    private PaymentFixture() {}

    public static long requestPayment(final String 구매자_token, final long 주문_id) {
        return Long.parseLong(
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
                                        .formatted(주문_id))
                        .when()
                        .post("/payments")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(LOCATION, matchesRegex("/payments/[0-9]+"))
                        .extract()
                        .header(LOCATION)
                        .split("/")[2]);
    }
}
