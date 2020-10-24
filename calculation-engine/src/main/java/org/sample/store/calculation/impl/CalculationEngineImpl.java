package org.sample.store.calculation.impl;

import org.sample.store.calculation.CalculationEngine;
import org.sample.store.calculation.api.*;
import org.sample.store.calculation.rule.CalculationRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculationEngineImpl implements CalculationEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationEngineImpl.class);

    @Autowired
    private List<CalculationRule> calculationRules;

    @Override
    public CalculationResponse calculate(CalculationContext context) throws CalculationException {
        LOGGER.info("Executing calculation-engine for received context [{}]", context);

        for (CalculationRule rule: calculationRules) {
            try {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Current Context [{}] Executing Calculation Rule [{}]", context, rule.getId());
                }

                context = rule.executeRule(context);
            } catch (Exception e) {
                LOGGER.error("Error occurred while performing calculation ", e);
                throw new CalculationException("E1000", e.getLocalizedMessage(), context);
            }
        }
        return retrieveCalculationResponse(context);
    }

    private CalculationResponse retrieveCalculationResponse(CalculationContext context) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieved updated-calculation context after executing calculation-rules [{}]", context);
        }
        List<ProductCalculationResponse> productResponses = retrieveProductLevelResponses(context.getProducts());
        float overallAmount = calculateOverallAmount(productResponses);
        return new CalculationResponse(productResponses, overallAmount);
    }

    private float calculateOverallAmount(List<ProductCalculationResponse> productResponses) {
        return productResponses.stream().map(ProductCalculationResponse::getTotalAmount).reduce(0f, Float::sum);
    }

    private List<ProductCalculationResponse> retrieveProductLevelResponses(List<ProductCalculationContext> productContexts) {
        return productContexts.stream().map(this::retrieveProductResponse).collect(Collectors.toList());
    }

    private ProductCalculationResponse retrieveProductResponse(ProductCalculationContext productContext) {
        float totalAmount = calculateTotalAmountPerProduct(productContext);
        return new ProductCalculationResponse
                .Builder(productContext.getBasicDetails(), productContext.getRequiredUnits())
                .withNumberOfCratons(productContext.getNumberOfCratons())
                .withNumberOfSingleUnits(productContext.getNumberOfSingleUnits())
                .withTotalAmount(totalAmount)
                .build();
    }

    private float calculateTotalAmountPerProduct(ProductCalculationContext productContext) {
        float cartonPrize = productContext.getUpdatedCartonPrize();
        float singleUnitPrice = productContext.getUpdatedSingleUnitPrice();
        float priceForPurchasedCartons = cartonPrize * productContext.getNumberOfCratons();
        float priceForPurchasedSingleUnits = singleUnitPrice * productContext.getNumberOfSingleUnits();
        return priceForPurchasedCartons + priceForPurchasedSingleUnits;
    }

    @Override
    public void setCalculationRules(List<CalculationRule> calculationRules) {
        this.calculationRules = calculationRules;
    }
}
