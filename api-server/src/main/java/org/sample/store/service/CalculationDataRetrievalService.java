package org.sample.store.service;

import org.sample.store.api.ApiResponse;
import org.sample.store.api.CalculationRequest;
import org.sample.store.repository.product.Product;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CalculationDataRetrievalService {
    Mono<? extends ApiResponse> retrievePriceData(int pageNumber, List<Product> products);

    Mono<? extends ApiResponse> calculate(CalculationRequest request, Product product);
}
