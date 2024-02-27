package com.github.bbooong.bangumall.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BanguMallException extends RuntimeException {

    private final HttpStatus status;

    protected BanguMallException(final HttpStatus status, final String message) {
        super(message);

        this.status = status;
    }

    protected BanguMallException(
            final HttpStatus status, final String message, final Throwable cause) {
        super(message, cause);

        this.status = status;
    }
}
