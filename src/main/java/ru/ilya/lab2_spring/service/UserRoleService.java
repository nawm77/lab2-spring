package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.model.enums.Role;

import java.util.List;

public interface UserRoleService {
    UserRoleDTO findById(String id);
    UserRoleDTO findByRole(Role role);
    List<UserRoleDTO> findAll();
    void addUserRole(UserRoleDTO userRole);
    void updateUserRole(UserRoleDTO userRole);
    void deleteUserRole(UserRoleDTO userRole);
    void deleteUserRoleById(String id);
}

