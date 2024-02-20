package com.github.bbooong.bangumall.stock.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.github.bbooong.bangumall.core.exception.BanguMallException;

public class StockDifferentProductException extends BanguMallException {

    public StockDifferentProductException() {
        super(INTERNAL_SERVER_ERROR, "한 가지 상품의 재고만 불러와야 합니다.");
    }
}
