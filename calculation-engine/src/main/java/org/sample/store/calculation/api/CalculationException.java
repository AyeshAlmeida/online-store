package org.sample.store.calculation.api;

public class CalculationException extends Exception {
    private final String errorCode;
    private final String errorDescription;
    private final CalculationContext context;

    public CalculationException(String errorCode,
                                String errorDescription,
                                CalculationContext context) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.context = context;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public CalculationContext getContext() {
        return context;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CalculationException{");
        sb.append("errorCode='").append(errorCode).append('\'');
        sb.append(", errorDescription='").append(errorDescription).append('\'');
        sb.append(", context=").append(context);
        sb.append('}');
        return sb.toString();
    }
}
