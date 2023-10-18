package ru.ilya.lab2_spring.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserRoleDTO {
    private UUID id;
    private String role;
}
