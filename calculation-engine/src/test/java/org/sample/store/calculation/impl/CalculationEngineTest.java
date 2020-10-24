package org.sample.store.calculation.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sample.store.calculation.CalculationEngine;
import org.sample.store.calculation.api.*;
import org.sample.store.calculation.rule.CalculationRule;
import org.sample.store.calculation.rule.impl.CartonAndUnitCalculationRule;
import org.sample.store.calculation.rule.impl.CartonPriceCalculationRule;
import org.sample.store.calculation.rule.impl.SingleUnitPriceCalculationRule;
import org.sample.store.calculation.util.TestingUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculation Engine Test")
public class CalculationEngineTest {
    private static CalculationEngine calculationEngine;

    @BeforeAll
    static void init() {
        SingleUnitPriceCalculationRule singleUnitPriceCalculationRule = new SingleUnitPriceCalculationRule();
        singleUnitPriceCalculationRule.setCartonPriceRate(1.3f);

        CartonPriceCalculationRule cartonPriceCalculationRule = new CartonPriceCalculationRule();
        cartonPriceCalculationRule.setMinNumberOfCartonsRequired(3);
        cartonPriceCalculationRule.setCartonPriceRate(0.9f);

        List<CalculationRule> calculationRules = new ArrayList<>();
        calculationRules.add(new CartonAndUnitCalculationRule());
        calculationRules.add(cartonPriceCalculationRule);
        calculationRules.add(singleUnitPriceCalculationRule);

        calculationEngine = new CalculationEngineImpl();
        calculationEngine.setCalculationRules(calculationRules);
    }

    @ParameterizedTest(name = "Executing Carton Price Calculation [{0}]")
    @MethodSource
    void testCalculation(List<ProductCalculationContext> products) throws CalculationException {
        CalculationContext context = new CalculationContext(products);
        CalculationResponse response = calculationEngine.calculate(context);
        assertNotNull(response);
        assertEquals(context.getProducts().size(), response.getProducts().size());
    }

    static Stream<List<ProductCalculationContext>> testCalculation() {
        return IntStream.range(1, 10)
                .mapToObj(counter -> {
                    return IntStream
                            .range(0, counter)
                            .mapToObj(k -> String.format("Prod_%d", k))
                            .map(TestingUtil::fromProductName)
                            .collect(Collectors.toList());
                });
    }

    @DisplayName("Executing Carton Price Calculation on erroneous data-set")
    @Test
    void testCalculationError() throws Exception {
        List<ProductCalculationContext> products = getProductForErrorScenario();
        CalculationContext calculationContext = new CalculationContext(products);
        CalculationException calcException = assertThrows(CalculationException.class, () -> {
            calculationEngine.calculate(calculationContext);
        });
        assertNotNull(calcException);
        assertEquals("E1000", calcException.getErrorCode());
        assertNotNull(calcException.getContext());
    }

    private List<ProductCalculationContext> getProductForErrorScenario() {
        BasicProductDetails product1 = new BasicProductDetails("prod_1", 10, 300f);
        ProductCalculationContext context1 = new ProductCalculationContext(product1, 12);

        BasicProductDetails product2 = new BasicProductDetails("prod_2", 25, 500f);
        ProductCalculationContext context2 = new ProductCalculationContext(product2, 0);

        BasicProductDetails product3 = new BasicProductDetails("prod_3", 0, 350f);
        ProductCalculationContext context3 = new ProductCalculationContext(product3, 5);

        return Arrays.asList(context1, context2, context3);
    }
}
