package ru.ilya.lab2_spring.controller.v1.rest.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.service.UserService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

@Slf4j
public abstract class UserControllerBase {
    private final UserService userService;

    @Autowired
    protected UserControllerBase(UserService userService) {
        this.userService = userService;
    }

    protected ResponseEntity<UserDTO> createUser(UserDTO userDTO) throws IllegalArgumentRequestException {
        UserDTO user;
        try {
            user = userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    protected ResponseEntity<?> getUser(String userId, Boolean withOffers) {
        if ("-1".equals(userId)) {
            if (!withOffers) {
                return ResponseEntity.status(HttpStatus.FOUND).body(userService.findAll());
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(userService.findAllWithOffers());
            }
        } else {
            if(!withOffers) {
                return ResponseEntity.status(HttpStatus.FOUND).body(userService.findById(userId));
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(userService.findByIdWithOffers(userId));
            }
        }
    }

    protected ResponseEntity<UserDTO> updateUser(UserDTO userDTO) throws IllegalArgumentRequestException {
        HttpStatus status = userService.findAllByUsername(userDTO.getUsername()).isEmpty() ?
                HttpStatus.CREATED : HttpStatus.ACCEPTED;
        userService.update(userDTO);
        return ResponseEntity.status(status).body(userDTO);
    }

    protected ResponseEntity<?> deleteUser(String id) {
        userService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
