package org.sample.store.calculation.rule.impl;

import org.sample.store.calculation.api.CalculationContext;
import org.sample.store.calculation.api.ProductCalculationContext;
import org.sample.store.calculation.rule.CalculationRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCalculationRule implements CalculationRule {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCalculationRule.class);

    @Override
    public CalculationContext executeRule(CalculationContext context) {
        LOGGER.info("Received calculation context [{}] to execute [{}]", context, getId());
        List<ProductCalculationContext> updatedProductList = context.getProducts().stream()
                .map(this::updateProduct)
                .collect(Collectors.toList());
        return new CalculationContext(updatedProductList);
    }

    protected abstract ProductCalculationContext updateProduct(ProductCalculationContext productContext);
}
