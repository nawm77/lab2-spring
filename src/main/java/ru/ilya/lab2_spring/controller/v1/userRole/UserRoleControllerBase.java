package ru.ilya.lab2_spring.controller.v1.userRole;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.model.enums.Role;
import ru.ilya.lab2_spring.service.UserRoleService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

@Slf4j
public abstract class UserRoleControllerBase {
    private final UserRoleService userRoleService;

    protected UserRoleControllerBase(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    protected ResponseEntity<UserRoleDTO> createUserRole(UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException {
        UserRoleDTO userRole;
        try {
            userRole = userRoleService.save(userRoleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userRole);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    protected ResponseEntity<?> getUserRole(String id, Boolean withUsers) {
        if ("-1".equals(id)) {
            if (!withUsers) {
                return ResponseEntity.status(HttpStatus.FOUND).body(userRoleService.findAll());
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(userRoleService.findAllWithUsers());
            }
        } else {
            if (!withUsers) {
                return ResponseEntity.status(HttpStatus.FOUND).body(userRoleService.findById(id));
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(userRoleService.findByIdWithUsers(id));
            }
        }
    }

    protected ResponseEntity<UserRoleDTO> updateUserRole(UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException {
        HttpStatus status = userRoleService.findByRole(Role.fromCode(userRoleDTO.))
    }
}
