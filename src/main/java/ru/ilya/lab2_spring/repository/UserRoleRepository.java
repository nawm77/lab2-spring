package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.lab2_spring.entity.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findById(Long id);
    Optional<UserRole> findUserRoleByRole(String role);
}
