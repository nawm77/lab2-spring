package ru.ilya.lab2_spring.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import ru.ilya.lab2_spring.entity.enums.Role;

@Data
@Builder
public class UserRoleDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
}
