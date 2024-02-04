package com.github.bbooong.bangumall.order.infrastructure;

import com.github.bbooong.bangumall.order.domain.ProductClient;
import com.github.bbooong.bangumall.order.domain.ProductInfo;
import com.github.bbooong.bangumall.product.application.dto.ProductDetailResponse;
import com.github.bbooong.bangumall.product.ui.ProductController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductClientImpl implements ProductClient {

    private final ProductController productController;

    @Override
    public ProductInfo getProduct(final long productId) {
        final ProductDetailResponse response = productController.getProduct(productId);

        return new ProductInfo(response.id(), response.price());
    }
}
