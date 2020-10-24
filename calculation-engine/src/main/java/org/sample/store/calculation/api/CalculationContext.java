package org.sample.store.calculation.api;

import java.util.List;

public class CalculationContext {
    private final List<ProductCalculationContext> products;

    public CalculationContext(List<ProductCalculationContext> products) {
        this.products = products;
    }

    public List<ProductCalculationContext> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CalculationContext{");
        sb.append("products=").append(products);
        sb.append('}');
        return sb.toString();
    }
}
