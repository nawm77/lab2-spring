package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.BrandRepository;
import ru.ilya.lab2_spring.service.BrandService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;
    private final Mapper mapper;

    @Autowired
    public BrandServiceImpl(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    private BrandDTO add(BrandDTO b) throws EntityExistsException {
//        Optional<Brand> existingBrand = brandRepository.findAllByName(b.getName()).stream().findFirst();
//        if(existingBrand.isPresent()){
//            throw new EntityExistsException(String.format("Brand " + b + " already exists"));
//        }
        BrandDTO dto = mapper.toDTO(brandRepository.saveAndFlush(mapper.toEntity(b)));
        log.info("Create brand {} with id {}", dto, dto.getId());
        return dto;
    }

    @Override
    public List<BrandDTO> findAllByName(String name) {
        return brandRepository.findAllByName(name)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public BrandDTO findById(String id) {
        return mapper.toDTO(brandRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such brand with id " + id)));
    }

    @Override
    public void saveAll(List<BrandDTO> list) {
        for (BrandDTO brandDTO : list) {
            save(brandDTO);
        }
    }

    @Override
    public BrandDTO save(BrandDTO brand) throws EntityExistsException {
        return add(brand);
    }

    @Override
    public void deleteById(String id) {
        try {
            brandRepository.deleteById(id);
        } catch (Exception e){
            throw new NoSuchElementException("No such element with id : " + id);
        }
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
