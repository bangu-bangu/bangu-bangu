package com.github.bbooong.bangumall.product.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(long id);
}
