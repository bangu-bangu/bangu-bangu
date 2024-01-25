package com.github.bbooong.bangumall.member.ui;

import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.http.HttpHeaders.LOCATION;

import com.github.bbooong.bangumall.config.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("인수 테스트: Member")
@AcceptanceTest
class MemberControllerTest {

    @Nested
    @DisplayName("member를 추가할 때")
    class Describe_CreateMember {

        @Nested
        @DisplayName("이메일, 비밀번호를 알맞게 입력한 경우")
        class Context_With_ValidRequest {

            final String email = "test@email.com";
            final String password = "test";

            @Test
            @DisplayName("추가한 member의 id를 반환한다.")
            void it_returns_memberId() {
                RestAssured.given()
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
                        .post("/members")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .header(LOCATION, matchesRegex("/members/[0-9]+"));
            }
        }
    }
}
