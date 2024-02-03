package ru.ilya.lab2_spring.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ModelDTO {
    @JsonProperty("id")
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 1, message = "Model name must contains 1 character minimum")
    @JsonProperty("name")
    private String name;
    @NotEmpty
    @NotNull(message = "Category must be chosen")
    @Length(min = 1, message = "Incorrect category")
    @JsonProperty("category")
    private String category;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("startYear")
    private Integer startYear;
    @JsonProperty("endYear")
    private Integer endYear;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("brandDTO")
    private BrandDTO brandDTO;

    @JsonCreator
    public ModelDTO(@JsonProperty("id") String id,
                    @JsonProperty("name") String name,
                    @JsonProperty("category") String category,
                    @JsonProperty("imageUrl") String imageUrl,
                    @JsonProperty("startYear") Integer startYear,
                    @JsonProperty("endYear") Integer endYear,
                    @JsonProperty("created") String created,
                    @JsonProperty("modified") String modified,
                    @JsonProperty("brandDTO") BrandDTO brandDTO) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.brandDTO = brandDTO;
    }
}
