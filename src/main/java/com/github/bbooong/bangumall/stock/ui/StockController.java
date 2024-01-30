package com.github.bbooong.bangumall.stock.ui;

import com.github.bbooong.bangumall.stock.application.StockService;
import com.github.bbooong.bangumall.stock.application.dto.StockCreateRequest;
import com.github.bbooong.bangumall.stock.application.dto.StockInfoResponse;
import com.github.bbooong.bangumall.stock.application.dto.StockUpdateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/products/{productId}/stocks")
    public ResponseEntity<Void> createStock(
            @PathVariable final long productId, @RequestBody final StockCreateRequest request) {
        final long stockId = stockService.create(productId, request);

        return ResponseEntity.created(URI.create("/products/" + productId + "/stocks/" + stockId))
                .build();
    }

    @PutMapping("/stocks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockInfoResponse updateStock(
            @PathVariable final long id, @RequestBody final StockUpdateRequest request) {
        return stockService.update(id, request);
    }
}
