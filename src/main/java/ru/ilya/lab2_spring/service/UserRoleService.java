package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.model.enums.Role;
import ru.ilya.lab2_spring.model.viewModel.UserRoleWithUsersView;

import java.util.List;

public interface UserRoleService extends BaseService<UserRoleDTO> {
    UserRoleDTO findByRole(Role role);
    void deleteUserRole(UserRoleDTO userRole);
    List<UserRoleWithUsersView> findAllWithUsers();
    UserRoleWithUsersView findByIdWithUsers(String id);
}

