package org.sample.store.calculation.api;

public class ProductCalculationResponse {
    private final BasicProductDetails basicDetails;
    private final int requiredUnits;
    private final int numberOfCratons;
    private final int numberOfSingleUnits;
    private final float totalAmount;

    private ProductCalculationResponse(Builder builder) {
        this.basicDetails = builder.basicDetails;
        this.requiredUnits = builder.requiredUnits;
        this.numberOfCratons = builder.numberOfCratons;
        this.numberOfSingleUnits = builder.numberOfSingleUnits;
        this.totalAmount = builder.totalAmount;
    }

    public BasicProductDetails getBasicDetails() {
        return basicDetails;
    }

    public int getRequiredUnits() {
        return requiredUnits;
    }

    public int getNumberOfCratons() {
        return numberOfCratons;
    }

    public int getNumberOfSingleUnits() {
        return numberOfSingleUnits;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductCalculationResponse{");
        sb.append("basicDetails=").append(basicDetails);
        sb.append(", requiredUnits=").append(requiredUnits);
        sb.append(", numberOfCratons=").append(numberOfCratons);
        sb.append(", numberOfSingleUnits=").append(numberOfSingleUnits);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private final BasicProductDetails basicDetails;
        private final int requiredUnits;
        private int numberOfCratons;
        private int numberOfSingleUnits;
        private float totalAmount;

        public Builder(BasicProductDetails basicDetails, int requiredUnits) {
            this.basicDetails = basicDetails;
            this.requiredUnits = requiredUnits;
        }

        public Builder withNumberOfCratons(int numberOfCratons) {
            this.numberOfCratons = numberOfCratons;
            return this;
        }

        public Builder withNumberOfSingleUnits(int numberOfSingleUnits) {
            this.numberOfSingleUnits = numberOfSingleUnits;
            return this;
        }

        public Builder withTotalAmount(float totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public ProductCalculationResponse build() {
            return new ProductCalculationResponse(this);
        }
    }
}
