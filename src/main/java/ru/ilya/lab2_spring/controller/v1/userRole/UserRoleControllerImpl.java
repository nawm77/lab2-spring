package ru.ilya.lab2_spring.controller.v1.userRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.service.UserRoleService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

@RestController
public class UserRoleControllerImpl extends UserRoleControllerBase implements UserRoleController{

    @Autowired
    public UserRoleControllerImpl(UserRoleService userRoleService) {
        super(userRoleService);
    }

    @Override
    public ResponseEntity<UserRoleDTO> createUserRole(UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException {
        return super.createUserRole(userRoleDTO);
    }

    @Override
    public ResponseEntity<?> getUserRole(String roleId, Boolean withUsers) {
        return super.getUserRole(roleId, withUsers);
    }

    @Override
    public ResponseEntity<UserRoleDTO> updateUserRole(UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException {
        return super;
    }

    @Override
    public ResponseEntity<?> deleteUserRole(String roleId) {
        return super;
    }
}
