package ru.ilya.lab2_spring.service;

import java.util.List;

public interface BaseService <T>{
    List<T> findAll();
    T findById(String id);
    void deleteById(String id);

}
