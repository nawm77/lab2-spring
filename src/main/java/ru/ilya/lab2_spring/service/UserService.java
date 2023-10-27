package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.UserDTO;

public interface UserService extends BaseService<UserDTO> {
    UserDTO findByUsername(String username);

    void createUser(UserDTO user);

    void updateUser(UserDTO user);

    void deleteUser(UserDTO user);
}
