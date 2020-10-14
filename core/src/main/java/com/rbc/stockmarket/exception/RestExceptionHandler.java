package com.rbc.stockmarket.exception;

import com.rbc.stockmarket.dto.ErrorDto;
import com.rbc.stockmarket.exception.common.GeneralException;
import com.rbc.stockmarket.exception.storage.FileIsEmptyException;
import com.rbc.stockmarket.exception.storage.FileNotSupportedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = FileIsEmptyException.class)
    protected ResponseEntity<ErrorDto> handleFileIsEmpty(FileIsEmptyException e) {
        return buildResponseEntity(new ErrorDto(HttpStatus.BAD_REQUEST, e.getMessage(), e.getKey()));
    }

    @ExceptionHandler(value = FileNotSupportedException.class)
    protected ResponseEntity<ErrorDto> handleFileNotSupporting(FileNotSupportedException e) {
        return buildResponseEntity(new ErrorDto(HttpStatus.BAD_REQUEST, e.getMessage(), e.getKey()));
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorDto> internalServerError(Exception e) {
        GeneralException generalException = new GeneralException();
        return buildResponseEntity(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR,
                generalException.getMessage(), generalException.getKey()));
    }

    private ResponseEntity<ErrorDto> buildResponseEntity(ErrorDto errorDto) {
        return new ResponseEntity<>(errorDto, errorDto.getStatus());
    }
}
