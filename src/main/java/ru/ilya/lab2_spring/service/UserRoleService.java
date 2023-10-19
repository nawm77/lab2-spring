package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.UserRole;
import ru.ilya.lab2_spring.entity.enums.Role;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    UserRole findById(UUID id);
    UserRole findByRole(Role role);
    List<UserRole> findAll();
    void addUserRole(UserRole userRole);
    void updateUserRole(UserRole userRole);
    void deleteUserRole(UserRole userRole);
    void deleteUserRoleById(UUID id);
}

