package org.sample.store.calculation.api;

public class BasicProductDetails {
    private final String name;
    private final int unitsPerCarton;
    private final float cartonPrice;

    public BasicProductDetails(String name, int unitsPerCarton, float cartonPrice) {
        this.name = name;
        this.unitsPerCarton = unitsPerCarton;
        this.cartonPrice = cartonPrice;
    }

    public String getName() {
        return name;
    }

    public int getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public float getCartonPrice() {
        return cartonPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BasicProductDetails{");
        sb.append("name='").append(name).append('\'');
        sb.append(", unitsPerCarton=").append(unitsPerCarton);
        sb.append(", cartonPrice=").append(cartonPrice);
        sb.append('}');
        return sb.toString();
    }
}
