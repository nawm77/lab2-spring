package ru.ilya.lab2_spring.model.viewModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ilya.lab2_spring.dto.BrandDTO;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class BrandModelViewModel implements Serializable {
    private BrandDTO brandDTO;
    private List<ModelWithOutBrandView> modelWithOutBrandViews;
//    @JsonCreator
//    public BrandModelViewModel(@JsonProperty("brandDTO") BrandDTO brandDTO,
//                               @JsonProperty("modelWithOutBrandViews") List<ModelWithOutBrandView> modelWithOutBrandViews) {
//        this.brandDTO = brandDTO;
//        this.modelWithOutBrandViews = modelWithOutBrandViews;
//    }
}
