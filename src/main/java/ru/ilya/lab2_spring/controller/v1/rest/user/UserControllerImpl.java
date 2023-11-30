package ru.ilya.lab2_spring.controller.v1.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.service.UserService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

@RestController
public class UserControllerImpl extends UserControllerBase implements UserController{
    @Autowired
    protected UserControllerImpl(UserService userService) {
        super(userService);
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) throws IllegalArgumentRequestException {
        return super.createUser(userDTO);
    }

    @Override
    public ResponseEntity<?> getUser(String userId, Boolean withOffers) {
        return super.getUser(userId, withOffers);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) throws IllegalArgumentRequestException {
        return super.updateUser(userDTO);
    }

    @Override
    public ResponseEntity<?> deleteUser(String id) {
        return super.deleteUser(id);
    }
}
