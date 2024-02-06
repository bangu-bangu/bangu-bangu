package com.github.bbooong.bangumall.core.exception;

import com.github.bbooong.bangumall.core.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BanguMallException.class)
    public ResponseEntity<ErrorResponse> handleBanguMallException(final BanguMallException e) {
        log.warn("{}", e.getClass().getSimpleName(), e);

        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        final BanguMallUnexpectedException unexpectedException = new BanguMallUnexpectedException();
        log.error("{}", unexpectedException.getClass().getSimpleName(), e);

        return ResponseEntity.status(unexpectedException.getStatus())
                .body(new ErrorResponse(unexpectedException.getMessage()));
    }
}
