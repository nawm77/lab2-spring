package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    void deleteUserById(Long id);
}
