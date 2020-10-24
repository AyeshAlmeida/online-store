package org.sample.store.calculation;

import org.sample.store.calculation.api.CalculationContext;
import org.sample.store.calculation.api.CalculationException;
import org.sample.store.calculation.api.CalculationResponse;
import org.sample.store.calculation.rule.CalculationRule;

import java.util.List;

public interface CalculationEngine {
    CalculationResponse calculate(CalculationContext context) throws CalculationException;

    void setCalculationRules(List<CalculationRule> calculationRules);
}
