package com.github.bbooong.bangumall.order.infrastructure;

import com.github.bbooong.bangumall.order.domain.StockClient;
import com.github.bbooong.bangumall.stock.application.dto.StockDecreaseRequest;
import com.github.bbooong.bangumall.stock.ui.StockController;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockClientImpl implements StockClient {

    private final StockController stockController;

    @Override
    public void decreaseStocks(final List<StockDecreaseRequest> requests) {
        stockController.decreaseStocks(requests);
    }
}
