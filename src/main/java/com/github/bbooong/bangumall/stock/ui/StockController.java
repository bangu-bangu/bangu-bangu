package com.github.bbooong.bangumall.stock.ui;

import com.github.bbooong.bangumall.stock.application.StockService;
import com.github.bbooong.bangumall.stock.application.dto.StockCreateRequest;
import com.github.bbooong.bangumall.stock.application.dto.StockDecreaseRequest;
import com.github.bbooong.bangumall.stock.application.dto.StockInfoResponse;
import com.github.bbooong.bangumall.stock.application.dto.StockUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/products/{productId}/stocks")
    @ResponseStatus(HttpStatus.OK)
    public List<StockInfoResponse> getStock(@PathVariable @Positive final long productId) {
        return stockService.getStocks(productId);
    }

    @PostMapping("/products/{productId}/stocks")
    public ResponseEntity<Void> createStock(
            @PathVariable @Positive final long productId,
            @Valid @RequestBody final StockCreateRequest request) {
        final long stockId = stockService.create(productId, request);

        return ResponseEntity.created(URI.create("/products/" + productId + "/stocks/" + stockId))
                .build();
    }

    @PutMapping("/stocks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockInfoResponse updateStock(
            @PathVariable @Positive final long id,
            @Valid @RequestBody final StockUpdateRequest request) {
        return stockService.update(id, request);
    }

    @PostMapping("/stocks/decrease")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseStocks(@Valid @RequestBody final List<StockDecreaseRequest> requests) {
        stockService.decreaseStocks(requests);
    }
}
