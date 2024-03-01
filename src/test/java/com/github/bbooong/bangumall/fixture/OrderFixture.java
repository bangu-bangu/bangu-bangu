package com.github.bbooong.bangumall.fixture;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.restassured.RestAssured;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

public abstract class OrderFixture {

    private OrderFixture() {}

    public static long order(final String 구매자_token, final List<Map<String, Object>> orderLines) {
        return Long.parseLong(
                RestAssured.given()
                        .contentType(APPLICATION_JSON_VALUE)
                        .auth()
                        .oauth2(구매자_token)
                        .body(
                                """
                    {
                        "orderLines": %s
                    }
                    """
                                        .formatted(toJson(orderLines)))
                        .when()
                        .post("/orders")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(LOCATION, matchesRegex("/orders/[0-9]+"))
                        .extract()
                        .header(LOCATION)
                        .split("/")[2]);
    }

    private static String toJson(final List<Map<String, Object>> orderLines) {
        return orderLines.stream()
                .map(
                        orderLine ->
                                orderLine.entrySet().stream()
                                        .map(
                                                entry ->
                                                        "\""
                                                                + entry.getKey()
                                                                + "\": "
                                                                + entry.getValue())
                                        .collect(Collectors.joining(",", "{", "}")))
                .collect(Collectors.joining(",", "[", "]"));
    }
}
