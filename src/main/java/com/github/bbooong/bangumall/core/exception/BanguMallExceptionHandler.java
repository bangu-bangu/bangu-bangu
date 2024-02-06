package com.github.bbooong.bangumall.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BanguMallExceptionHandler {

    @ExceptionHandler(BanguMallException.class)
    public ResponseEntity<BanguMallException> handleBanguMallException(final BanguMallException e) {
        return ResponseEntity.status(e.getStatus()).body(e);
    }
}
