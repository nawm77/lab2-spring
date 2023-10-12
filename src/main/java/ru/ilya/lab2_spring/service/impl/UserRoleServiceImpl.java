package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.entity.UserRole;
import ru.ilya.lab2_spring.repository.UserRoleRepository;
import ru.ilya.lab2_spring.service.UserRoleService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole findById(Long id) {
        return userRoleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such user role with id" + id));
    }

    @Override
    public UserRole findByRoleName(String roleName) {
        return userRoleRepository.findUserRoleByRole(roleName).orElseThrow(() -> new NoSuchElementException("No such role " + roleName));
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public void addUserRole(UserRole userRole) {
        userRoleRepository.saveAndFlush(userRole);
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        userRoleRepository.saveAndFlush(userRole);
    }

    @Override
    public void deleteUserRole(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }

    @Override
    public void deleteUserRoleById(Long id) {
        userRoleRepository.deleteById(id);
    }
}
