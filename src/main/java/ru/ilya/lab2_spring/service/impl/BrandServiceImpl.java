package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.BrandRepository;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

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
        list.forEach(this::save);
    }

    @Override
    public BrandDTO save(BrandDTO brand) throws EntityExistsException {
        log.info("Create brand {} with id {} and name {}", brand, brand.getId(), brand.getName());
        return saveOrUpdate(brand);
    }

    @Override
    public void deleteById(String id) {
        try {
            brandRepository.deleteById(id);
            log.info("Delete brand with id {}", id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) {
        if(brandRepository.findById(brandDTO.getId()).isPresent()){
            log.info("Update brand {}", brandDTO);
        }
        return saveOrUpdate(brandDTO);
    }

    @Override
    public void deleteAll(List<BrandDTO> brandDTOList) throws IllegalArgumentRequestException {
        for (BrandDTO dto : brandDTOList){
            delete(dto);
        }
    }

    @Override
    public void delete(BrandDTO brand) throws IllegalArgumentRequestException {
        try {
            brandRepository.delete(mapper.toEntity(brand));
        } catch (Exception e){
            throw new IllegalArgumentRequestException(e.getLocalizedMessage());
        }
    }

    private BrandDTO saveOrUpdate(BrandDTO b) throws EntityExistsException {
        return mapper.toDTO(brandRepository.saveAndFlush(mapper.toEntity(b)));
    }
}
