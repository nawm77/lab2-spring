package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.lab2_spring.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Short> {
}
