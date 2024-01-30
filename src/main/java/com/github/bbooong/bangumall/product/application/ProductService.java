package com.github.bbooong.bangumall.product.application;

import com.github.bbooong.bangumall.product.application.dto.ProductCreateRequest;
import com.github.bbooong.bangumall.product.application.dto.ProductDetailResponse;
import com.github.bbooong.bangumall.product.application.dto.ProductInfoResponse;
import com.github.bbooong.bangumall.product.domain.Product;
import com.github.bbooong.bangumall.product.domain.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public long createProduct(final ProductCreateRequest request) {
        final Product product =
                Product.create(request.name(), request.price(), request.description());
        return productRepository.save(product).getId();
    }

    public List<ProductInfoResponse> getProducts() {
        return productRepository.findAll().stream().map(ProductInfoResponse::from).toList();
    }

    public ProductDetailResponse getProduct(final long id) {
        return productRepository.findById(id).map(ProductDetailResponse::from).orElseThrow();
    }
}
