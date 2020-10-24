package org.sample.store.api;

public class CalculationRequest {
    private int productId;
    private String type;
    private int quantity;
    private float currentTotal;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(float currentTotal) {
        this.currentTotal = currentTotal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CalculationRequest{");
        sb.append("productId=").append(productId);
        sb.append(", type='").append(type).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", currentTotal=").append(currentTotal);
        sb.append('}');
        return sb.toString();
    }
}
