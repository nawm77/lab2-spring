package ru.ilya.lab2_spring.service;

import jakarta.persistence.EntityExistsException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;

public interface BrandService {
    List<BrandDTO> findAll();
    List<BrandDTO> findAllByName(String name);
    BrandDTO findById(String id);
    void saveAll(List<BrandDTO> list) throws IllegalArgumentRequestException;
    void save(BrandDTO brand) throws EntityExistsException;
    void deleteById(String id);
    void deleteAll(List<BrandDTO> list);
    void delete(BrandDTO brand);
}
