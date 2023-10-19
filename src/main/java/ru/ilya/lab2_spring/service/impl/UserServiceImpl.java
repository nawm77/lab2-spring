package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.UserRepository;
import ru.ilya.lab2_spring.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO findById(UUID id) {
        return mapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such user with id " + id)));
    }

    @Override
    public UserDTO findByUsername(String username) {
        return mapper.toDTO(userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("No such user " + username)));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void createUser(UserDTO user) {
        userRepository.save(mapper.toEntity(user));
    }

    @Override
    public void updateUser(UserDTO user) {
        createUser(user);
    }

    @Override
    public void deleteUser(UserDTO user) {
        userRepository.delete(mapper.toEntity(user));
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
