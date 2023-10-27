package ru.ilya.lab2_spring.model.viewModel;

import lombok.Builder;
import lombok.Data;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.dto.ModelDTO;

import java.util.List;

@Data
@Builder
public class BrandModelViewModel {
    private BrandDTO brandDTO;
    private List<ModelDTO> modelDTOList;
}
