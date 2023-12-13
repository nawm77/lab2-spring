package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static ru.ilya.lab2_spring.service.util.Constants.REDIS_BRANDS_AND_MODELS_CACHE_NAME;
import static ru.ilya.lab2_spring.service.util.Constants.REDIS_BRANDS_CACHE_NAME;

@Service
@Slf4j
@EnableCaching
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
        //TODO возможное решение - использовать класс - обертку, в котором будет 1 поле - лист ДТО. Тогда можно будет десериализовать массив в поле и отдать лист ДТО
        return brandRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }


    @Override
    @Cacheable(key = "#name", value = REDIS_BRANDS_CACHE_NAME)
    public List<BrandDTO> findAllByName(String name) {
        return brandRepository.findAllByName(name)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
//    @Cacheable(key = "#id", value = REDIS_BRANDS_CACHE_NAME)
    public BrandDTO findById(String id) {
        System.out.println(id);
        return mapper.toDTO(brandRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such brand with id " + id)));
    }

    @Override
    @Transactional
    public BrandDTO save(BrandDTO brand) throws EntityExistsException, IllegalArgumentRequestException {
        BrandDTO dto = saveOrUpdate(brand);
        log.info("Create brand {} with id {} and name {}", dto, dto.getId(), dto.getName());
        return dto;
    }

    @Override
    @CacheEvict(key = "#id", value = REDIS_BRANDS_CACHE_NAME)
    public void deleteById(String id) {
        try {
            brandRepository.deleteById(id);
            log.info("Delete brand with id {}", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @Override
    @Transactional
    @CacheEvict(key = "#brandDTO.id", value = REDIS_BRANDS_CACHE_NAME)
    @CachePut(key = "#brandDTO.id", value = REDIS_BRANDS_CACHE_NAME)
    public BrandDTO update(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        BrandDTO exists = findById(brandDTO.getId());
        brandDTO.setCreated(exists.getCreated());
        brandDTO.setModified(LocalDateTime.now().toString());
        log.info("Update brand {} to {}", exists, brandDTO);
        return saveOrUpdate(brandDTO);
    }

    @Override
    @CacheEvict(key = "#brand.id", value = REDIS_BRANDS_CACHE_NAME)
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
//    @Cacheable(key = "#id", value = REDIS_BRANDS_AND_MODELS_CACHE_NAME)
    public BrandModelViewModel findByIdWithModel(String id) {
        return brandRepository.findById(id).stream()
                .map(b -> BrandModelViewModel.builder()
                        .brandDTO(mapper.toDTO(b))
                        .modelWithOutBrandViews(b.getModel().stream()
                                .map(mapper::toView)
                                .toList())
                        .build())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such brand with id " + id));
    }

    private BrandDTO saveOrUpdate(BrandDTO brandDTO) throws EntityExistsException, IllegalArgumentRequestException {
        brandDTO.setModified(LocalDateTime.now().toString());
        validationUtil.validateDTO(brandDTO);
        try {
            return mapper.toDTO(brandRepository.saveAndFlush(mapper.toEntity(brandDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }
}
