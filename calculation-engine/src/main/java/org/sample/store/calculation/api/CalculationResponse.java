package org.sample.store.calculation.api;

import java.util.List;

public class CalculationResponse {
    private final List<ProductCalculationResponse> products;
    private final float totalAmount;

    public CalculationResponse(List<ProductCalculationResponse> products, float totalAmount) {
        this.products = products;
        this.totalAmount = totalAmount;
    }

    public List<ProductCalculationResponse> getProducts() {
        return products;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CalculationResponse{");
        sb.append("products=").append(products);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append('}');
        return sb.toString();
    }
}
