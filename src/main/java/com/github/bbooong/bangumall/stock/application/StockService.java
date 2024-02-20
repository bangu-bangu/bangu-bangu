package com.github.bbooong.bangumall.stock.application;

import com.github.bbooong.bangumall.stock.application.dto.StockCreateRequest;
import com.github.bbooong.bangumall.stock.application.dto.StockDecreaseRequest;
import com.github.bbooong.bangumall.stock.application.dto.StockInfoResponse;
import com.github.bbooong.bangumall.stock.application.dto.StockUpdateRequest;
import com.github.bbooong.bangumall.stock.domain.Stock;
import com.github.bbooong.bangumall.stock.domain.StockRepository;
import com.github.bbooong.bangumall.stock.domain.Stocks;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public StockInfoResponse update(final long id, final StockUpdateRequest request) {
        final Stock stock = stockRepository.findById(id).orElseThrow();

        stock.update(request.quantity(), request.expiredDate());

        return StockInfoResponse.from(stock);
    }

    @Transactional
    public long create(final long productId, final StockCreateRequest request) {
        return stockRepository
                .save(Stock.create(productId, request.quantity(), request.expiredDate()))
                .getId();
    }

    @Transactional(readOnly = true)
    public List<StockInfoResponse> getStocks(final long productId) {
        return stockRepository.findAllByProductId(productId).stream()
                .map(StockInfoResponse::from)
                .toList();
    }

    @Transactional
    public void decreaseStocks(final List<StockDecreaseRequest> requests) {
        for (final StockDecreaseRequest request : requests) {
            final Stocks stocks =
                    Stocks.create(
                            stockRepository.findAllExclusivelyByProductIdOrderByExpiredDate(
                                    request.productId()));
            stocks.decreaseQuantity(request.quantity());
        }
    }
}
