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

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carton and Unit Count Calculation Test")
public class CartonAndUnitCalculationRuleTest {
    private static CartonAndUnitCalculationRule rule;

    @BeforeAll
    static void init() {
        rule = new CartonAndUnitCalculationRule();
    }

    @ParameterizedTest(name = "Testing Product Context Update for [{0}/{1}/{2}] [{3}]")
    @CsvSource({
            "Prod_1, 10, 500, 35",
            "Prod_2, 25, 800, 12",
            "Prod_3, 100, 1000, 100"
    })
    void testUpdateProduct(@AggregateWith(ProductCalculationContextAggregator.class) ProductCalculationContext context) {
        ProductCalculationContext updatedContext = rule.updateProduct(context);
        BasicProductDetails basicDetails = context.getBasicDetails();
        int expectedNumberOfCartons = context.getRequiredUnits() / basicDetails.getUnitsPerCarton();
        int expectedNumberOfSingleUnits = context.getRequiredUnits() % basicDetails.getUnitsPerCarton();
        assertNotNull(updatedContext);
        assertEquals(expectedNumberOfCartons, updatedContext.getNumberOfCratons());
        assertEquals(expectedNumberOfSingleUnits, updatedContext.getNumberOfSingleUnits());
    }

    @ParameterizedTest(name = "Executing Carton and Unit Calculation Rule on [{0}]")
    @MethodSource
    void testExecuteRule(List<ProductCalculationContext> products) {
        CalculationContext context = new CalculationContext(products);
        CalculationContext updatedContext = rule.executeRule(context);
        assertNotNull(updatedContext);
        updatedContext.getProducts().forEach(product -> {
            BasicProductDetails basicDetails = product.getBasicDetails();
            int expectedNumberOfCartons = product.getRequiredUnits() / basicDetails.getUnitsPerCarton();
            int expectedNumberOfSingleUnits = product.getRequiredUnits() % basicDetails.getUnitsPerCarton();
            assertEquals(expectedNumberOfCartons, product.getNumberOfCratons());
            assertEquals(expectedNumberOfSingleUnits, product.getNumberOfSingleUnits());
        });
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
