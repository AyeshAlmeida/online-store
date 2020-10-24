package org.sample.store.service.impl;

import org.sample.store.api.ApiResponse;
import org.sample.store.api.PriceData;
import org.sample.store.api.PriceDataResponse;
import org.sample.store.calculation.CalculationEngine;
import org.sample.store.calculation.api.*;
import org.sample.store.repository.product.Product;
import org.sample.store.service.CalculationDataRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CalculationDataRetrievalServiceImpl implements CalculationDataRetrievalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationDataRetrievalServiceImpl.class);

    private final CalculationEngine calculationEngine;
    private int totalCalculationForPriceData = 50;
    private int totalPricesPerRequest = 10;

    @Autowired
    public CalculationDataRetrievalServiceImpl(CalculationEngine calculationEngine) {
        this.calculationEngine = calculationEngine;
    }

    @Override
    public Mono<? extends ApiResponse> retrievePriceData(int pageNumber, List<Product> products) {
        LOGGER.info("Retrieving price date for page[{}] products[{}]", pageNumber, products);

        List<CalculationContext> calculationContexts = generateCalculationContexts(pageNumber, products);

        List<PriceData> prices = new ArrayList<>();

        for (CalculationContext context: calculationContexts) {
            try {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Dispatching calculation context [{}] for calculation", context);
                }
                CalculationResponse response = calculationEngine.calculate(context);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Retrieved calculation response [{}]", response);
                }

                PriceData priceData = retrievePriceData(response);

                prices.add(priceData);
            } catch (CalculationException calcException) {
                LOGGER.error("Error occurred while executing calculation context ", calcException);
                return Mono.error(calcException);
            }
        }
        return Mono.fromCallable(() -> PriceDataResponse.success(totalCalculationForPriceData, prices));
    }

    private List<CalculationContext> generateCalculationContexts(int pageNumber, List<Product> products) {
        int startIdx = pageNumber * totalPricesPerRequest + 1;
        return IntStream
                    .range(startIdx, startIdx + totalPricesPerRequest)
                    .mapToObj(counter -> products.stream()
                            .map(p -> retrieveProductContext(p, counter))
                            .collect(Collectors.toList()))
                    .map(CalculationContext::new)
                    .collect(Collectors.toList());
    }

    private ProductCalculationContext retrieveProductContext(Product product, int numberOfUnits) {
        BasicProductDetails details = new BasicProductDetails(
                product.getProductName(), product.getPacksPerCarton(), product.getCartonPrice());
        return new ProductCalculationContext(details, numberOfUnits);
    }

    private PriceData retrievePriceData(CalculationResponse response) {
        List<ProductCalculationResponse> products = response.getProducts();
        PriceData data = new PriceData();
        data.setQuantity(products.get(0).getRequiredUnits());
        data.setPenguinEarPrice(products.get(0).getTotalAmount());
        data.setHorseShoePrice(products.get(1).getTotalAmount());
        return data;
    }

    @Override
    public Mono<? extends ApiResponse> calculate(float currentAmount, Product product) {
        return null;
    }
}
