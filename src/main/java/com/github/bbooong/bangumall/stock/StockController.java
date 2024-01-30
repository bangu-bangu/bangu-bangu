package com.github.bbooong.bangumall.stock;

import com.github.bbooong.bangumall.stock.application.dto.StockCreateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    @PostMapping("/products/{productId}/stocks")
    public ResponseEntity<Void> createStock(
            @PathVariable final long productId, @RequestBody final StockCreateRequest request) {
        final long stockId = 1L;

        return ResponseEntity.created(URI.create("/products/" + productId + "/stocks/" + stockId))
                .build();
    }
}
