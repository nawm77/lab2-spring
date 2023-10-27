package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.model.enums.Role;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.UserRoleRepository;
import ru.ilya.lab2_spring.service.UserRoleService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final Mapper mapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, Mapper mapper) {
        this.userRoleRepository = userRoleRepository;
        this.mapper = mapper;
    }

    @Override
    public UserRoleDTO findById(String id) {
        return mapper.toDTO(userRoleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user role with id" + id)));
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public UserRoleDTO update(UserRoleDTO object) {
        return null;
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
    public void addUserRole(UserRoleDTO userRole) {
        userRoleRepository.save(mapper.toEntity(userRole));
    }

    @Override
    public void updateUserRole(UserRoleDTO userRole) {
        userRoleRepository.save(mapper.toEntity(userRole));
    }

    @Override
    public void deleteUserRole(UserRoleDTO userRole) {
        userRoleRepository.delete(mapper.toEntity(userRole));
    }

    @Override
    public void deleteUserRoleById(String id) {
        userRoleRepository.deleteById(id);
    }
}
