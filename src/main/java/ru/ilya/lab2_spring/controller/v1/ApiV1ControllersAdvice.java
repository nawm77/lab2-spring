package ru.ilya.lab2_spring.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.model.api.ApiErrorResponse;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ApiV1ControllersAdvice {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> onResponseStatusException(ResponseStatusException exception) {
        logWarn(exception);
        return ResponseEntity.status(exception.getStatusCode()).body(
                ApiErrorResponse.builder()
                        .httpStatus(HttpStatus.resolve(exception.getStatusCode().value()))
                        .message(exception.getLocalizedMessage())
                        .build()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> onIllegalArgumentException(IllegalArgumentRequestException exception) {
        logWarn(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiErrorResponse.builder()
                        .message(exception.getLocalizedMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build()
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> onNoSuchElementException(NoSuchElementException exception) {
        logWarn(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiErrorResponse.builder()
                        .message(exception.getLocalizedMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build()
        );
    }


    //todo посмотреть реализацию АОП для логгирования методов с ошибками
    private void logWarn(Exception e) {
        log.warn(e.getMessage(), e);
    }
}
