package org.sample.store.calculation.rule;

import org.sample.store.calculation.api.CalculationContext;

public interface CalculationRule {
    String getId();

    CalculationContext executeRule(CalculationContext context);
}
