package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.entity.Brand;
import ru.ilya.lab2_spring.repository.BrandRepository;
import ru.ilya.lab2_spring.service.BrandService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    public void add(Brand b){
        brandRepository.saveAndFlush(b);
    }

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }
    @Override
    public Brand findById(Long id){
        return brandRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such brand with id" + id));
    }

    @Override
    public void saveAll(List<Brand> list) {
        list.forEach(this::save);
    }

    @Override
    public void save(Brand brand) {
        brandRepository.saveAndFlush(brand);
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Brand> list) {
        list.forEach(this::delete);
    }

    @Override
    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }
}
