package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO findById(UUID id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
    void createUser(UserDTO user);
    void updateUser(UserDTO user);
    void deleteUser(UserDTO user);
    void deleteUserById(UUID id);
}
