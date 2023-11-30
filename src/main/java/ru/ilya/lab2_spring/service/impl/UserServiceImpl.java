package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.model.viewModel.UserOfferViewModel;
import ru.ilya.lab2_spring.repository.UserRepository;
import ru.ilya.lab2_spring.service.UserService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final Mapper mapper;
    private final ValidationUtil validationUtil;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(Mapper mapper, ValidationUtil validationUtil) {
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findById(String id) {
        return mapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such user with id " + id)));
    }

    @Override
    public void deleteById(String id) {
        try {
            userRepository.deleteById(id);
            log.info("Delete user with id {}", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    @Override
    public UserDTO update(UserDTO userDTO) throws IllegalArgumentRequestException {
        if(userRepository.findById(userDTO.getId()).isPresent()){
            log.info("Update user {}", userDTO);
        }
        return saveOrUpdate(userDTO);
    }

    @Override
    public UserDTO save(UserDTO object) throws IllegalArgumentRequestException, EntityExistsException {
        UserDTO dto = saveOrUpdate(object);
        log.info("Create user {} with id {} and username {}", dto, dto.getId(), dto.getUsername());
        return dto;
    }

    private UserDTO saveOrUpdate(UserDTO userDTO) throws EntityExistsException, IllegalArgumentRequestException {
        //todo затестить сохранение с несколькими оффермаи и тд  то есть с вложенными сущностями
        userDTO.setModified(LocalDateTime.now());
        validationUtil.validateDTO(userDTO);
        try {
            return mapper.toDTO(userRepository.saveAndFlush(mapper.toEntity(userDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    @Override
    public List<UserDTO> findAllByUsername(String username) {
        return userRepository.findAllByUsername(username)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<UserOfferViewModel> findAllWithOffers() {
        return userRepository.findAll().stream()
                .map(u -> UserOfferViewModel.builder()
                        .userDTO(mapper.toDTO(u))
                        .offerDTOList(u.getOfferList().stream().map(mapper::toDTO).toList())
                        .build())
                .toList();
    }

    @Override
    public UserOfferViewModel findByIdWithOffers(String userId) {
        return userRepository.findById(userId).stream()
                .map(u -> UserOfferViewModel.builder()
                        .userDTO(mapper.toDTO(u))
                        .offerDTOList(u.getOfferList().stream()
                                .map(mapper::toDTO)
                                .toList())
                        .build())
                .findFirst().orElseThrow(() -> new NoSuchElementException("No such user with id " + userId));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}