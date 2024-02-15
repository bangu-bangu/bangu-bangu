package com.github.bbooong.bangumall.core.exception;

import com.github.bbooong.bangumall.core.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BanguMallException.class)
    public ResponseEntity<ErrorResponse> handleBanguMallException(final BanguMallException e) {
        log.warn("{}", e.getClass().getSimpleName(), e);

        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({
        MethodArgumentNotValidException.class,
        MethodArgumentTypeMismatchException.class,
        HandlerMethodValidationException.class
    })
    public ResponseEntity<ErrorResponse> handleValidationException(final Exception e) {
        log.warn("{}", e.getClass().getSimpleName(), e);

        final BanguMallFormatException formatException = new BanguMallFormatException();

        return ResponseEntity.status(formatException.getStatus())
                .body(new ErrorResponse(formatException.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        final BanguMallUnexpectedException unexpectedException = new BanguMallUnexpectedException();
        log.error("{}", unexpectedException.getClass().getSimpleName(), e);

        return ResponseEntity.status(unexpectedException.getStatus())
                .body(new ErrorResponse(unexpectedException.getMessage()));
    }
}
