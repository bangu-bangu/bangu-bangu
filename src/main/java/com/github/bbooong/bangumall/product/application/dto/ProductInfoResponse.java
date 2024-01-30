package com.github.bbooong.bangumall.product.application.dto;

import com.github.bbooong.bangumall.product.domain.Product;

public record ProductInfoResponse(long id, String name, int price) {

    public static ProductInfoResponse from(final Product product) {
        return new ProductInfoResponse(product.getId(), product.getName(), product.getPrice());
    }
}
