package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.model.enums.Role;
import ru.ilya.lab2_spring.model.viewModel.UserRoleWithUsersView;
import ru.ilya.lab2_spring.repository.UserRoleRepository;
import ru.ilya.lab2_spring.service.UserRoleService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final Mapper mapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, Mapper mapper, ValidationUtil validationUtil) {
        this.userRoleRepository = userRoleRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserRoleDTO findById(String id) {
        return mapper.toDTO(userRoleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such user role with id" + id)));
    }

    @Override
    public void deleteById(String id) {
        try {
            userRoleRepository.deleteById(id);
            log.info("Delete user role with id {}", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    @Override
    public UserRoleDTO update(UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException {
        if (userRoleRepository.findById(userRoleDTO.getId()).isPresent()) {
            log.info("Update user role {}", userRoleDTO);
        }
        return saveOrUpdate(userRoleDTO);
    }

    @Override
    public UserRoleDTO save(UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException, EntityExistsException {
        UserRoleDTO dto = saveOrUpdate(userRoleDTO);
        log.info("Create new user role {} with id {} and name {}", dto, dto.getId(), dto.getRole());
        return dto;
    }

    @Override
    public UserRoleDTO findByRole(Role role) {
        return mapper.toDTO(userRoleRepository.findUserRoleByRole(role).orElseThrow(() -> new NoSuchElementException("No such role " + role)));
    }

    @Override
    public List<UserRoleDTO> findAll() {
        return userRoleRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void deleteUserRole(UserRoleDTO userRole) {
        userRoleRepository.delete(mapper.toEntity(userRole));
    }

    @Override
    public List<UserRoleWithUsersView> findAllWithUsers() {
        return userRoleRepository.findAll().stream()
                .map(ur -> UserRoleWithUsersView.builder()
                    .id(ur.getId())
                    .roleName(ur.getRole().name())
                    .users(ur.getUsers().stream().map(mapper::toDTO).toList())
                    .build())
                .toList();
    }

    @Override
    public UserRoleWithUsersView findByIdWithUsers(String id) {
        return userRoleRepository.findById(id).stream()
                .map(ur -> UserRoleWithUsersView.builder()
                        .id(ur.getId())
                        .roleName(ur.getRole().name())
                        .users(ur.getUsers().stream().map(mapper::toDTO).toList())
                        .build())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such user role with id: id"));
    }

    private UserRoleDTO saveOrUpdate(UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException {
        userRoleDTO.setModified(LocalDateTime.now());
        validationUtil.validateDTO(userRoleDTO);
        try {
            return mapper.toDTO(userRoleRepository.saveAndFlush(mapper.toEntity(userRoleDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }
}
