package org.sample.store.calculation.util;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.sample.store.calculation.api.BasicProductDetails;
import org.sample.store.calculation.api.ProductCalculationContext;

public class ProductCalculationContextAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        BasicProductDetails details = new BasicProductDetails(
                accessor.getString(0),
                accessor.getInteger(1),
                accessor.getFloat(2));
        return new ProductCalculationContext(details, accessor.getInteger(3));
    }
}
