package com.github.bbooong.bangumall.fixture;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;

import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public abstract class ProductFixture {

    private ProductFixture() {}

    public static long create(final String name, final int price) {
        return Long.parseLong(
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
                        .header(LOCATION, matchesRegex("/products/[0-9]+"))
                        .extract()
                        .header(LOCATION)
                        .split("/")[2]);
    }
}
