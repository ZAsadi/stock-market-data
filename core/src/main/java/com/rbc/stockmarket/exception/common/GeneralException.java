package com.rbc.stockmarket.exception.common;

import com.rbc.stockmarket.exception.StockMarketRuntimeException;

public class GeneralException extends StockMarketRuntimeException {

    private static final String KEY = "GENERAL_ERROR";

    public GeneralException() {
        super(KEY, "General error occurred in server.");
    }
}
