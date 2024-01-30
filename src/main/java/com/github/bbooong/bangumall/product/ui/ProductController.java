package com.github.bbooong.bangumall.product.ui;

import com.github.bbooong.bangumall.product.application.ProductService;
import com.github.bbooong.bangumall.product.application.dto.ProductCreateRequest;
import com.github.bbooong.bangumall.product.application.dto.ProductInfoResponse;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody final ProductCreateRequest request) {
        final long productId = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/products/" + productId)).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInfoResponse> getProducts() {
        return productService.getProducts();
    }
}
