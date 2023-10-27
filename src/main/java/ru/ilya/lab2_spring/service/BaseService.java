package ru.ilya.lab2_spring.service;

import jakarta.persistence.EntityExistsException;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;

public interface BaseService <T>{
    List<T> findAll();
    T findById(String id);
    void deleteById(String id);
    T update(T object) throws IllegalArgumentRequestException;
    T save(T object) throws IllegalArgumentRequestException, EntityExistsException;
}
