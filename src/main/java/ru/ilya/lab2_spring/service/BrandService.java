package ru.ilya.lab2_spring.service;

import org.springframework.boot.ssl.SslBundleKey;
import ru.ilya.lab2_spring.entity.Brand;
import ru.ilya.lab2_spring.repository.BrandRepository;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<Brand> findAll();
    Brand findById(UUID id);
    void saveAll(List<Brand> list);
    void save(Brand brand);
    void deleteById(UUID id);
    void deleteAll(List<Brand> list);
    void delete(Brand brand);
}
