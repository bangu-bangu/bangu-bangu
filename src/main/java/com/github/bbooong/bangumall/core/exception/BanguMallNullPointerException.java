package com.github.bbooong.bangumall.core.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class BanguMallNullPointerException extends BanguMallException {

    public BanguMallNullPointerException() {
        super(INTERNAL_SERVER_ERROR, "null 값은 허용되지 않습니다.");
    }
}
