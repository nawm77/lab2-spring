package ru.ilya.lab2_spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO implements Serializable {
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Brand name must contains minimum two characters")
    private String name;
    private String created;
    private String modified;
}
