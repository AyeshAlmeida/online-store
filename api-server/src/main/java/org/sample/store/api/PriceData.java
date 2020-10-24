package org.sample.store.api;

public class PriceData {
    private int quantity;
    private float penguinEarPrice;
    private float horseShoePrice;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPenguinEarPrice() {
        return penguinEarPrice;
    }

    public void setPenguinEarPrice(float penguinEarPrice) {
        this.penguinEarPrice = penguinEarPrice;
    }

    public float getHorseShoePrice() {
        return horseShoePrice;
    }

    public void setHorseShoePrice(float horseShoePrice) {
        this.horseShoePrice = horseShoePrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriceData{");
        sb.append("quantity=").append(quantity);
        sb.append(", penguinEarPrice=").append(penguinEarPrice);
        sb.append(", horseShoePrice=").append(horseShoePrice);
        sb.append('}');
        return sb.toString();
    }
}
