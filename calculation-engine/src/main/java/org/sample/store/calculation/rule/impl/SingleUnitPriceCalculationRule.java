package org.sample.store.calculation.rule.impl;

import org.sample.store.calculation.api.BasicProductDetails;
import org.sample.store.calculation.api.ProductCalculationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class SingleUnitPriceCalculationRule extends AbstractCalculationRule {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleUnitPriceCalculationRule.class);

    @Value("#{T(java.lang.Float).parseFloat('${calculation.engine.rule-config.single-unite-price-calculation.carton-price-rate}')}")
    private float cartonPriceRate = 1.3f;

    @Override
    public String getId() {
        return "SingleUnitPriceCalculation";
    }

    @Override
    protected ProductCalculationContext updateProduct(ProductCalculationContext productContext) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Received product context to calculate single-unit-price [{}]", productContext);
        }

        BasicProductDetails basicDetails = productContext.getBasicDetails();
        float updatedCartonPrize = productContext.getUpdatedCartonPrize();
        int unitsPerCarton = basicDetails.getUnitsPerCarton();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Updated carton-price [{}] Units per carton [{}] price-update-rate [{}]",
                    updatedCartonPrize, unitsPerCarton, cartonPriceRate);
        }

        float updatedSingleUnitPrice = (updatedCartonPrize * cartonPriceRate) / unitsPerCarton;

        return productContext.updateSingleUnitPrice(updatedSingleUnitPrice);
    }

    public void setCartonPriceRate(float cartonPriceRate) {
        this.cartonPriceRate = cartonPriceRate;
    }
}
