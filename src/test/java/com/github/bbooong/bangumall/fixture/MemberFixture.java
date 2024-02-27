package com.github.bbooong.bangumall.fixture;

import static org.springframework.http.HttpHeaders.LOCATION;

import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public abstract class MemberFixture {

    private MemberFixture() {}

    public static Long createMember(final String email, final String password, final String role) {
        final String location =
                RestAssured.given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(
                                """
                                        {
                                            "email": "%s",
                                            "password": "%s",
                                            "role": "%s"
                                        }
                                        """
                                        .formatted(email, password, role))
                        .when()
                        .post("/members")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .header(LOCATION)
                        .split("/")[2];

        return Long.valueOf(location);
    }
}
