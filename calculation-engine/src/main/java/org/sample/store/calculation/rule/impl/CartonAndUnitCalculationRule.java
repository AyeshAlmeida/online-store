package org.sample.store.calculation.rule.impl;

import org.sample.store.calculation.api.BasicProductDetails;
import org.sample.store.calculation.api.ProductCalculationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CartonAndUnitCalculationRule extends AbstractCalculationRule {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartonAndUnitCalculationRule.class);

    @Override
    public String getId() {
        return "CartonAndUnitCalculationRule";
    }

    @Override
    protected ProductCalculationContext updateProduct(ProductCalculationContext productContext) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Received product context to calculate unit and carton number [{}]", productContext);
        }

        BasicProductDetails basicDetails = productContext.getBasicDetails();
        int numberOfRequiredUnits = productContext.getRequiredUnits();
        int requiredNumberOfCartons = numberOfRequiredUnits / basicDetails.getUnitsPerCarton();
        int requiredNumberOfSingleUnits = numberOfRequiredUnits % basicDetails.getUnitsPerCarton();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[{}] number of cartons and [{}] number of single-units required of [{}]",
                    requiredNumberOfCartons, requiredNumberOfSingleUnits, basicDetails.getName());
        }

        return productContext.updateNumSingleUnitsAndCratons(requiredNumberOfCartons, requiredNumberOfSingleUnits);
    }
}
