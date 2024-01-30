package com.github.bbooong.bangumall.product.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @PostMapping
    public ResponseEntity<Void> createProduct() {
        return ResponseEntity.created(URI.create("/products/" + 0)).build();
    }
}
