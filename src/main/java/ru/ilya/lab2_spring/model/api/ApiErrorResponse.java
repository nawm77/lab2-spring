package ru.ilya.lab2_spring.model.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ApiErrorResponse {
    private HttpStatus httpStatus;
    private String message;
}
