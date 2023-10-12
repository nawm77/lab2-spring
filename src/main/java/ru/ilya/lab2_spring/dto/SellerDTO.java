package ru.ilya.lab2_spring.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SellerDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private UserRoleDTO userRoleDTO;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;
}
