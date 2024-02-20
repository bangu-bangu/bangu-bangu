package com.github.bbooong.bangumall.stock.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.github.bbooong.bangumall.core.exception.BanguMallException;

public class StockQuantityNotEnoughException extends BanguMallException {

    public StockQuantityNotEnoughException() {
        super(BAD_REQUEST, "수량이 부족합니다.");
    }
}
