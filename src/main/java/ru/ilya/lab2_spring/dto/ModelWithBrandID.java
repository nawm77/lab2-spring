package ru.ilya.lab2_spring.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Data
public class ModelWithBrandID {
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
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    private String created;
    private String modified;
    private String brandId;

    @JsonCreator
    public ModelWithBrandID(@JsonProperty("name") String name,
                    @JsonProperty("category") String category,
                    @JsonProperty("imageUrl") String imageUrl,
                    @JsonProperty("startYear") Integer startYear,
                    @JsonProperty("endYear") Integer endYear,
                    @JsonProperty("created") String created,
                    @JsonProperty("modified") String modified,
                    @JsonProperty("brandId") String brandId) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.brandId = brandId;
    }
}
