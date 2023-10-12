package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole findById(Long id);
    UserRole findByRoleName(String roleName);
    List<UserRole> findAll();
    void addUserRole(UserRole userRole);
    void updateUserRole(UserRole userRole);
    void deleteUserRole(UserRole userRole);
    void deleteUserRoleById(Long id);
}

