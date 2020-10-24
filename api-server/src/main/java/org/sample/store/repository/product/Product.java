package org.sample.store.repository.product;

public class Product {
    private final long id;
    private final String productName;
    private final int packsPerCarton;
    private final float cartonPrice;

    private Product(Builder builder) {
        this.id = builder.id;
        this.productName = builder.productName;
        this.packsPerCarton = builder.packsPerCarton;
        this.cartonPrice = builder.cartonPrice;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getPacksPerCarton() {
        return packsPerCarton;
    }

    public float getCartonPrice() {
        return cartonPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", packsPerCarton=").append(packsPerCarton);
        sb.append(", cartonPrice=").append(cartonPrice);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private long id;
        private String productName;
        private int packsPerCarton;
        private float cartonPrice;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withPacksPerCarton(int packsPerCarton) {
            this.packsPerCarton = packsPerCarton;
            return this;
        }

        public Builder withCartonPrice(float cartonPrice) {
            this.cartonPrice = cartonPrice;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
