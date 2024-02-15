package com.github.bbooong.bangumall.product.ui;

import com.github.bbooong.bangumall.product.application.ProductService;
import com.github.bbooong.bangumall.product.application.dto.ProductCreateRequest;
import com.github.bbooong.bangumall.product.application.dto.ProductDetailResponse;
import com.github.bbooong.bangumall.product.application.dto.ProductInfoResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(
            @Valid @RequestBody final ProductCreateRequest request) {
        final long productId = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/products/" + productId)).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInfoResponse> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetailResponse getProduct(@PathVariable final long id) {
        return productService.getProduct(id);
    }
}
