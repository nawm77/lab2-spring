package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.BrandRepository;
import ru.ilya.lab2_spring.service.BrandService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final Mapper mapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, Mapper mapper) {
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }
    public void add(BrandDTO b){
        mapper.toDTO(brandRepository.save(mapper.toEntity(b)));
    }

    public List<BrandDTO> findAll(){
        return brandRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
    @Override
    public BrandDTO findById(UUID id){
        return mapper.toDTO(brandRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such brand with id" + id)));
    }

    @Override
    public void saveAll(List<BrandDTO> list) {
        list.forEach(this::save);
    }

    @Override
    public void save(BrandDTO brand) {
        brandRepository.saveAndFlush(mapper.toEntity(brand));
    }

    @Override
    public void deleteById(UUID id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<BrandDTO> list) {
        list.forEach(this::delete);
    }

    @Override
    public void delete(BrandDTO brand) {
        brandRepository.delete(mapper.toEntity(brand));
    }
}
