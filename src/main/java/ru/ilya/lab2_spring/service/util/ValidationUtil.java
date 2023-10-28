package ru.ilya.lab2_spring.service.util;

import jakarta.validation.ConstraintViolation;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.Set;

public interface ValidationUtil {
    <T> boolean isValid(T object);

    <T> Set<ConstraintViolation<T>> violations(T object);
    <T> void validateDTO(T dto) throws IllegalArgumentRequestException;
}
