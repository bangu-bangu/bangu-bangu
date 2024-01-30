package com.github.bbooong.bangumall.product.application.dto;

import com.github.bbooong.bangumall.product.domain.Product;

public record ProductDetailResponse(long id, String name, int price, String description) {

    public static ProductDetailResponse from(final Product product) {
        return new ProductDetailResponse(
                product.getId(), product.getName(), product.getPrice(), product.getDescription());
    }
}
