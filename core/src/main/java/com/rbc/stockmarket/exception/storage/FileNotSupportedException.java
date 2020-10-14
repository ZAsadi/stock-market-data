package com.rbc.stockmarket.exception.storage;

import com.rbc.stockmarket.exception.StockMarketRuntimeException;
import lombok.Getter;

@Getter
public class FileNotSupportedException extends StockMarketRuntimeException {

    private static final String KEY = "FILE_NOT_SUPPORTED";

    public FileNotSupportedException(String message) {
        super(KEY, message);
    }
}
