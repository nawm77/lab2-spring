package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User findById(UUID id);
    User findByUsername(String username);
    List<User> findAll();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    void deleteUserById(UUID id);
}
