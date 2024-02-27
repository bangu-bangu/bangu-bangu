package com.github.bbooong.bangumall.fixture;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.restassured.RestAssured;
import java.time.LocalDate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class StockFixture {

    private StockFixture() {}

    public static long create(
            final String token,
            final long productId,
            final int quantity,
            final LocalDate expiredDate) {
        return Long.parseLong(
                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
                        .auth()
                        .oauth2(token)
                        .body(
                                """
                                        {
                                            "quantity": %s,
                                            "expiredDate": "%s"
                                        }
                                        """
                                        .formatted(quantity, expiredDate))
                        .when()
                        .post("/products/{productId}/stocks", productId)
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .header(HttpHeaders.LOCATION)
                        .split("/")[4]);
    }
}
