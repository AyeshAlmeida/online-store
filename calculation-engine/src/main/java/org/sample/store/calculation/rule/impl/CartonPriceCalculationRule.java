package org.sample.store.calculation.rule.impl;

import org.sample.store.calculation.api.BasicProductDetails;
import org.sample.store.calculation.api.ProductCalculationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CartonPriceCalculationRule extends AbstractCalculationRule {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartonPriceCalculationRule.class);

    @Value("${calculation.engine.rule-config.carton-price-calculation.min-number-of-cartons-required}")
    private int minNumberOfCartonsRequired = 3;
    @Value("#{T(java.lang.Float).parseFloat('${calculation.engine.rule-config.carton-price-calculation.carton-price-rate}')}")
    private float cartonPriceRate = 0.9f;

    @Override
    public String getId() {
        return "CartonPriceCalculationRule";
    }

    @Override
    protected ProductCalculationContext updateProduct(ProductCalculationContext productContext) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Received product context to calculate carton-price [{}]", productContext);
        }

        BasicProductDetails basicDetails = productContext.getBasicDetails();

        int numberOfCartonsRequired = productContext.getNumberOfCratons();

        float currentCartonPrice = basicDetails.getCartonPrice();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Required [{}] cartons and min-number of cartons required carton-price-update [{}] and current-carton-price [{}]",
                    numberOfCartonsRequired, minNumberOfCartonsRequired, currentCartonPrice);
        }

        if (numberOfCartonsRequired >= minNumberOfCartonsRequired) {
            float updatedCartonPrice = currentCartonPrice * cartonPriceRate;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Buying more than min-number of cartons for discount, updated carton price [{}]",
                        updatedCartonPrice);
            }
            return productContext.updateCratonPrice(updatedCartonPrice);
        } else {
            return productContext.updateCratonPrice(currentCartonPrice);
        }
    }

    public void setMinNumberOfCartonsRequired(int minNumberOfCartonsRequired) {
        this.minNumberOfCartonsRequired = minNumberOfCartonsRequired;
    }

    public void setCartonPriceRate(float cartonPriceRate) {
        this.cartonPriceRate = cartonPriceRate;
    }
}
