package com.rbc.stockmarket.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class StockMarketRuntimeException extends RuntimeException {
    private String key;

    protected StockMarketRuntimeException(@NonNull String key, @NonNull String message) {
        super(message);
        this.key = key;
    }
}
