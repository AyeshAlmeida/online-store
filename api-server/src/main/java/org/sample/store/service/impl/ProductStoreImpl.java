package org.sample.store.service.impl;

import org.sample.store.repository.product.Product;
import org.sample.store.repository.product.ProductRepository;
import org.sample.store.service.ProductStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ProductStoreImpl implements ProductStore {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductStoreImpl.class);

    private final List<Product> products = new ArrayList<>();
    private final ProductRepository repository;

    @Autowired
    public ProductStoreImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        products.addAll(repository.getProducts());

        LOGGER.info("** Instantiated Product-Store **");
        LOGGER.info("** Product Store [{}] **", products);
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieving product for product-id [{}]", productId);
        }
        return products.stream().filter(p -> p.getId() == productId).findFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieving all products available..");
        }
        return products;
    }
}
