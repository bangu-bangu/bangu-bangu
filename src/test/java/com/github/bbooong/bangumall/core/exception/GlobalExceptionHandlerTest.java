package com.github.bbooong.bangumall.core.exception;

import static org.hamcrest.Matchers.is;

import com.github.bbooong.bangumall.config.AcceptanceTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@AcceptanceTest
@DisplayName("인수 테스트: 전역 예외 처리")
class GlobalExceptionHandlerTest {

    @Nested
    @DisplayName("클라이언트가 요청을 보낼 때")
    class Describe_ClientRequest {

        @Nested
        @DisplayName("body의 형식을 잘못 입력한 경우")
        class Context_With_InvalidBody {

            @Test
            @DisplayName("BAD_REQUEST를 반환한다.")
            void it_returns_badRequest() {
                RestAssured.given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body("{}")
                        .when()
                        .post("/test/validation/request-body")
                        .then()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .body("message", is("잘못된 형식의 요청입니다."));
            }
        }

        @Nested
        @DisplayName("path variable의 형식을 잘못 입력한 경우")
        class Context_With_InvalidPathVariable {

            @Test
            @DisplayName("BAD_REQUEST를 반환한다.")
            void it_returns_badRequest() {
                RestAssured.given()
                        .when()
                        .get("/test/validation/path-variable/%s".formatted(-1))
                        .then()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .body("message", is("잘못된 형식의 요청입니다."));
            }
        }

        @Nested
        @DisplayName("request param의 형식을 잘못 입력한 경우")
        class Context_With_InvalidRequestParam {

            @Test
            @DisplayName("BAD_REQUEST를 반환한다.")
            void it_returns_badRequest() {
                RestAssured.given()
                        .when()
                        .get("/test/validation/request-param?id=%s".formatted(-1))
                        .then()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .body("message", is("잘못된 형식의 요청입니다."));
            }
        }
    }
}
