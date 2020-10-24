package org.sample.store.api;

public class ProductCalculationData {
    private long id;
    private String product;
    private String type;
    private int quantity;
    private float amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductCalculationData{");
        sb.append("id=").append(id);
        sb.append(", product='").append(product).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
