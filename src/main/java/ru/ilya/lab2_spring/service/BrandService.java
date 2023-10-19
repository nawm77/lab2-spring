package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.BrandDTO;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<BrandDTO> findAll();
    BrandDTO findById(UUID id);
    void saveAll(List<BrandDTO> list);
    void save(BrandDTO brand);
    void deleteById(UUID id);
    void deleteAll(List<BrandDTO> list);
    void delete(BrandDTO brand);
}
