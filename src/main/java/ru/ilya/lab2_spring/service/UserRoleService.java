package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.entity.UserRole;
import ru.ilya.lab2_spring.entity.enums.Role;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    UserRoleDTO findById(UUID id);
    UserRoleDTO findByRole(Role role);
    List<UserRoleDTO> findAll();
    void addUserRole(UserRoleDTO userRole);
    void updateUserRole(UserRoleDTO userRole);
    void deleteUserRole(UserRoleDTO userRole);
    void deleteUserRoleById(UUID id);
}

