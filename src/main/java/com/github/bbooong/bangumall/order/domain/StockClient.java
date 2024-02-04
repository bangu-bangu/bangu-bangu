package com.github.bbooong.bangumall.order.domain;

import com.github.bbooong.bangumall.stock.application.dto.StockDecreaseRequest;
import java.util.List;

public interface StockClient {

    void decreaseStocks(List<StockDecreaseRequest> requests);
}
