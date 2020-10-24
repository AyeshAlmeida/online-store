package org.sample.store.calculation.util;

import org.sample.store.calculation.api.BasicProductDetails;
import org.sample.store.calculation.api.ProductCalculationContext;

import java.util.Random;

public final class TestingUtil {
    public static ProductCalculationContext fromProductName(String productName) {
        Random random = new Random();
        int unitsPerCarton = random.nextInt(50) + 25;
        float cartonPrice = random.nextFloat() + 300;
        int requiredUnits = random.nextInt(100) + 10;
        BasicProductDetails basicDetails = new BasicProductDetails(productName, unitsPerCarton, cartonPrice);
        return new ProductCalculationContext(basicDetails, requiredUnits);
    }

    public static float getExpectedCartonPrice(ProductCalculationContext updatedContext,
                                               BasicProductDetails basicDetails) {
        float expectedCartonPrice = basicDetails.getCartonPrice();
        if (updatedContext.getNumberOfCratons() >= 3) {
            expectedCartonPrice = basicDetails.getCartonPrice() * 0.9f;
        }
        return expectedCartonPrice;
    }

    public static float getExpectedUnitPrice(ProductCalculationContext context, BasicProductDetails details) {
        return (context.getUpdatedCartonPrize() * 1.3f) / details.getUnitsPerCarton();
    }
}
