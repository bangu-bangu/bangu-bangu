package com.github.bbooong.bangumall.core.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BanguMallFormatException extends BanguMallException {

    public BanguMallFormatException() {
        super(BAD_REQUEST, "잘못된 형식의 요청입니다.");
    }
}
