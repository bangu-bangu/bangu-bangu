package com.github.bbooong.bangumall.core.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BanguMallException.class)
    public ResponseEntity<BanguMallException> handleBanguMallException(final BanguMallException e) {
        log.warn("{}", e.getClass().getSimpleName(), e);

        return ResponseEntity.status(e.getStatus()).body(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(final Exception e) {
        log.error("{}", BanguMallUnexpectedException.class.getSimpleName(), e);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new BanguMallUnexpectedException());
    }
}
