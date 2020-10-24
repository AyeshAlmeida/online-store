package org.sample.store.service;

import org.sample.store.repository.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductStore {
    Optional<Product> getProduct(long productId);

    List<Product> getAllProducts();
}
