package com.github.bbooong.bangumall.stock.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.github.bbooong.bangumall.core.exception.BanguMallException;

public class StockQuantityNegativeException extends BanguMallException {

    public StockQuantityNegativeException() {
        super(BAD_REQUEST, "재고 수량은 음수가 될 수 없습니다.");
    }
}
