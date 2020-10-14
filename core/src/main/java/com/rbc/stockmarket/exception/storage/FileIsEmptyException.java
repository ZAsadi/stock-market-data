package com.rbc.stockmarket.exception.storage;

import com.rbc.stockmarket.exception.StockMarketRuntimeException;
import lombok.Getter;

@Getter
public class FileIsEmptyException extends StockMarketRuntimeException {

    private static final String KEY = "FILE_IS_EMPTY";

    public FileIsEmptyException(String message) {
        super(KEY, message);
    }
}
