package com.github.bbooong.bangumall.core.exception;

import org.springframework.http.HttpStatus;

public class BanguMallUnexpectedException extends BanguMallException {

    public BanguMallUnexpectedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 않은 오류가 발생했습니다.");
    }
}
