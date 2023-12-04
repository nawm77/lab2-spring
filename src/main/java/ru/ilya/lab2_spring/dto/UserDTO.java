package ru.ilya.lab2_spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 8, message = "Username must contains minimum 8 symbols")
    private String username;
    @NotNull
    @NotEmpty
    @Length(min = 8, message = "Password must contains minimum 8 symbols")
    private String password;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    private Boolean isActive;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;
    private UserRoleDTO userRoleDTO;
}
