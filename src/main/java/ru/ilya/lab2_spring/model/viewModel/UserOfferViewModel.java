package ru.ilya.lab2_spring.model.viewModel;

import lombok.Builder;
import lombok.Data;
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.dto.UserDTO;

import java.util.List;

@Data
@Builder
public class UserOfferViewModel {
    private UserDTO userDTO;
    private List<OfferDTO> offerDTOList;
}
