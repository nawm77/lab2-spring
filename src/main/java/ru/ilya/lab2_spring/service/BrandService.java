package ru.ilya.lab2_spring.service;

import org.springframework.boot.ssl.SslBundleKey;
import ru.ilya.lab2_spring.entity.Brand;
import ru.ilya.lab2_spring.repository.BrandRepository;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand findById(Long id);
    void saveAll(List<Brand> list);
    void save(Brand brand);
    void deleteById(Long id);
    void deleteAll(List<Brand> list);
    void delete(Brand brand);
}
