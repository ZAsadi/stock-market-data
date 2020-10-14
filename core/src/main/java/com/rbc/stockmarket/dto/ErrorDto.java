package com.rbc.stockmarket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorDto {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String key;

    private ErrorDto() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorDto(@NonNull HttpStatus status,@NonNull String message,@NonNull String key) {
        this();
        this.status = status;
        this.message = message;
        this.key = key;
    }
}
