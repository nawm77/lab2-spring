package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.model.viewModel.BrandModelViewModel;
import ru.ilya.lab2_spring.repository.BrandRepository;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;
    private final Mapper mapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(Mapper mapper, ValidationUtil validationUtil) {
        this.mapper = mapper;
        this.validationUtil = validationUtil;
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
    public BrandDTO save(BrandDTO brand) throws EntityExistsException, IllegalArgumentRequestException {
        BrandDTO dto = saveOrUpdate(brand);
        log.info("Create brand {} with id {} and name {}", dto, dto.getId(), dto.getName());
        return dto;
    }

    @Override
    public void deleteById(String id) {
        try {
            brandRepository.deleteById(id);
            log.info("Delete brand with id {}", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        if (brandRepository.findById(brandDTO.getId()).isPresent()) {
            log.info("Update brand {}", brandDTO);
        }
        return saveOrUpdate(brandDTO);
    }

    @Override
    public void delete(BrandDTO brand) throws IllegalArgumentRequestException {
        try {
            brandRepository.delete(mapper.toEntity(brand));
        } catch (Exception e) {
            throw new IllegalArgumentRequestException(e.getLocalizedMessage());
        }
    }

    @Override
    public List<BrandModelViewModel> findAllWithModels() {
        return brandRepository.findAll().stream()
                .map(b -> BrandModelViewModel.builder()
                        .brandDTO(mapper.toDTO(b))
                        .modelWithOutBrandViews(b.getModel().stream()
                                .map(mapper::toView).toList())
                        .build())
                .toList();
    }

    @Override
    public BrandModelViewModel findByIdWithModel(String id) {
        return brandRepository.findById(id).stream()
                .map(b -> BrandModelViewModel.builder()
                        .brandDTO(mapper.toDTO(b))
                        .modelWithOutBrandViews(b.getModel().stream()
                                .map(mapper::toView)
                                .toList())
                        .build())
                .findFirst().orElseThrow(() -> new NoSuchElementException("No such brand with id " + id));
    }

    private BrandDTO saveOrUpdate(BrandDTO brandDTO) throws EntityExistsException, IllegalArgumentRequestException {
        validationUtil.validateDTO(brandDTO);
        try {
            return mapper.toDTO(brandRepository.saveAndFlush(mapper.toEntity(brandDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }
}
