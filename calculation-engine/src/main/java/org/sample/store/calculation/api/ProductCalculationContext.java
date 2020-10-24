package org.sample.store.calculation.api;

public class ProductCalculationContext {
    private final BasicProductDetails basicDetails;
    private final int requiredUnits;
    private float updatedCartonPrize;
    private float updatedSingleUnitPrice;
    private int numberOfCratons;
    private int numberOfSingleUnits;

    public ProductCalculationContext(BasicProductDetails basicDetails,
                                     int requiredUnits) {
        this.basicDetails = basicDetails;
        this.requiredUnits = requiredUnits;
    }

    public ProductCalculationContext updateNumSingleUnitsAndCratons(int numberOfCratons, int numberOfSingleUnits) {
        ProductCalculationContext context = new ProductCalculationContext(this.basicDetails, this.requiredUnits);
        context.numberOfCratons = numberOfCratons;
        context.numberOfSingleUnits = numberOfSingleUnits;
        return context;
    }

    public ProductCalculationContext updateCratonPrice(float updatedCartonPrize) {
        ProductCalculationContext context = new ProductCalculationContext(this.basicDetails, this.requiredUnits);
        context.numberOfCratons = this.numberOfCratons;
        context.numberOfSingleUnits = this.numberOfSingleUnits;
        context.updatedCartonPrize = updatedCartonPrize;
        return context;
    }

    public ProductCalculationContext updateSingleUnitPrice(float updatedSingleUnitPrice) {
        ProductCalculationContext context = new ProductCalculationContext(this.basicDetails, this.requiredUnits);
        context.numberOfCratons = this.numberOfCratons;
        context.numberOfSingleUnits = this.numberOfSingleUnits;
        context.updatedCartonPrize = this.updatedCartonPrize;
        context.updatedSingleUnitPrice = updatedSingleUnitPrice;
        return context;
    }

    public BasicProductDetails getBasicDetails() {
        return basicDetails;
    }

    public int getRequiredUnits() {
        return requiredUnits;
    }

    public float getUpdatedCartonPrize() {
        return updatedCartonPrize;
    }

    public float getUpdatedSingleUnitPrice() {
        return updatedSingleUnitPrice;
    }

    public int getNumberOfCratons() {
        return numberOfCratons;
    }

    public int getNumberOfSingleUnits() {
        return numberOfSingleUnits;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductCalculationContext{");
        sb.append("basicDetails=").append(basicDetails);
        sb.append(", requiredUnits=").append(requiredUnits);
        sb.append(", updatedCartonPrize=").append(updatedCartonPrize);
        sb.append(", updatedSingleUnitPrice=").append(updatedSingleUnitPrice);
        sb.append(", numberOfCratons=").append(numberOfCratons);
        sb.append(", numberOfSingleUnits=").append(numberOfSingleUnits);
        sb.append('}');
        return sb.toString();
    }
}
