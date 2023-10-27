package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.lab2_spring.model.UserRole;
import ru.ilya.lab2_spring.model.enums.Role;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    Optional<UserRole> findUserRoleByRole(Role role);
}
