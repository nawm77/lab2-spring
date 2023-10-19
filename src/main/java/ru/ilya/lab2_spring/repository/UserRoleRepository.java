package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.lab2_spring.entity.UserRole;
import ru.ilya.lab2_spring.entity.enums.Role;

import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findById(UUID id);
    Optional<UserRole> findUserRoleByRole(Role role);
}
