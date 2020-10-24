package org.sample.store.calculation.rule.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.sample.store.calculation.api.BasicProductDetails;
import org.sample.store.calculation.api.CalculationContext;
import org.sample.store.calculation.api.ProductCalculationContext;
import org.sample.store.calculation.util.ProductCalculationContextAggregator;
import org.sample.store.calculation.util.TestingUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.sample.store.calculation.util.TestingUtil.getExpectedUnitPrice;

@DisplayName("Single Unit Price Calculation Test")
public class SingleUnitPriceCalculationTest {
    private static SingleUnitPriceCalculationRule rule;
    private static CartonPriceCalculationRule cartonPriceCalculationRule;
    private static CartonAndUnitCalculationRule cartonAndUnitCalculationRule;

    @BeforeAll
    static void init() {
        rule = new SingleUnitPriceCalculationRule();
        rule.setCartonPriceRate(1.3f);

        cartonPriceCalculationRule = new CartonPriceCalculationRule();
        cartonPriceCalculationRule.setMinNumberOfCartonsRequired(3);
        cartonPriceCalculationRule.setCartonPriceRate(0.9f);

        cartonAndUnitCalculationRule = new CartonAndUnitCalculationRule();
    }

    @ParameterizedTest(name = "Testing Product Context Update for [{0}/{1}/{2}] [{3}]")
    @CsvSource({
            "Prod_1, 10, 500, 35",
            "Prod_2, 25, 800, 12",
            "Prod_3, 100, 1000, 100"
    })
    void testUpdateProduct(@AggregateWith(ProductCalculationContextAggregator.class) ProductCalculationContext context) {
        ProductCalculationContext executableContext = getExecutableContext(context);

        ProductCalculationContext updatedContext = rule.updateProduct(executableContext);

        BasicProductDetails basicDetails = context.getBasicDetails();
        float expectedUnitPrice = getExpectedUnitPrice(updatedContext, basicDetails);

        assertNotNull(updatedContext);
        assertEquals(expectedUnitPrice, updatedContext.getUpdatedSingleUnitPrice());
    }

    private ProductCalculationContext getExecutableContext(ProductCalculationContext context) {
        ProductCalculationContext updatedContext = cartonAndUnitCalculationRule.updateProduct(context);
        return cartonPriceCalculationRule.updateProduct(updatedContext);
    }

    @ParameterizedTest(name = "Executing Carton Price Calculation Rule on [{0}]")
    @MethodSource
    void testExecuteRule(List<ProductCalculationContext> products) {
        CalculationContext context = new CalculationContext(products);
        CalculationContext executableContext = getExecutableContext(context);

        CalculationContext updatedContext = rule.executeRule(executableContext);
        assertNotNull(updatedContext);

        updatedContext.getProducts().forEach(product -> {
            BasicProductDetails basicDetails = product.getBasicDetails();
            float expectedUnitPrice = getExpectedUnitPrice(product, basicDetails);
            assertEquals(expectedUnitPrice, product.getUpdatedSingleUnitPrice());
        });
    }

    private CalculationContext getExecutableContext(CalculationContext context) {
        CalculationContext updatedContext = cartonAndUnitCalculationRule.executeRule(context);
        return cartonPriceCalculationRule.executeRule(updatedContext);
    }

    static Stream<List<ProductCalculationContext>> testExecuteRule() {
        return IntStream.range(1, 10)
                .mapToObj(counter -> {
                    return IntStream
                            .range(0, counter)
                            .mapToObj(k -> String.format("Prod_%d", k))
                            .map(TestingUtil::fromProductName)
                            .collect(Collectors.toList());
                });
    }
}
