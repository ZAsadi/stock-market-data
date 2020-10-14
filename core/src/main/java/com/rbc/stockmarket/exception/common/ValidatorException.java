package com.rbc.stockmarket.exception.common;

import com.rbc.stockmarket.exception.StockMarketRuntimeException;

public class ValidatorException extends StockMarketRuntimeException {

    private static final String KEY = "VALIDATION_ERROR";

    public ValidatorException(String message) {
        super(KEY, message);
    }
}
