package ru.ilya.lab2_spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    private String id;
    @NotNull
    @NotEmpty
    private String role;
    private LocalDateTime created;
    private LocalDateTime modified;
}
