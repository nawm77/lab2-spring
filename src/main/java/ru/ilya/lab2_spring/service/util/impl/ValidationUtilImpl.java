package ru.ilya.lab2_spring.service.util.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class ValidationUtilImpl implements ValidationUtil {
    private final Validator validator;
    private final List<String> exceptions = new CopyOnWriteArrayList<>();

    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }


    @Override
    public <E> boolean isValid(E object) {
        return this.validator.validate(object).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> violations(E object) {
        return this.validator.validate(object);
    }

    @Override
    public <T> void validateDTO(T dto) throws IllegalArgumentRequestException {
        if(!this.isValid(dto)){
            exceptions.clear();
            this.violations(dto).stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(exceptions::add);
            log.warn("Incorrect entity {}", dto);
            throw new IllegalArgumentRequestException(exceptions);
        }
    }
}
