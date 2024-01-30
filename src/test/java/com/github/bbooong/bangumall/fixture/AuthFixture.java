package com.github.bbooong.bangumall.fixture;

import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public abstract class AuthFixture {

    private AuthFixture() {}

    public static String login(final String email, final String password) {
        return RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(
                        """
            {
                "email": "%s",
                "password": "%s"
            }
            """
                                .formatted(email, password))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getString("token");
    }
}
