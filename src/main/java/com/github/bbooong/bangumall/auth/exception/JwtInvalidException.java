package com.github.bbooong.bangumall.auth.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.github.bbooong.bangumall.core.exception.BanguMallException;

public class JwtInvalidException extends BanguMallException {

    private static final String MESSAGE = "유효하지 않은 토큰입니다.";

    public JwtInvalidException() {
        super(UNAUTHORIZED, MESSAGE);
    }

    public JwtInvalidException(final Throwable cause) {
        super(UNAUTHORIZED, MESSAGE, cause);
    }
}
