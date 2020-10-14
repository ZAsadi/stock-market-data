package com.rbc.stockmarket.util.validator;

import com.rbc.stockmarket.dto.StockDto;

import javax.validation.*;
import java.util.Set;

public class Validator {
    public static void validateStockDto(StockDto stockDto) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<StockDto>> violations = validator.validate(stockDto);
        if (!violations.isEmpty()) {
            throw new ValidationException("There is some error in stock data");
        }
    }
}

