package com.shoestore.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoestore.client.dto.request.AddressDTO;
import com.shoestore.client.dto.request.BrandDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class AddressResponseDTO {
    private int totalItems;
    private int totalPages;
    @JsonProperty("address")
    private List<AddressDTO> address;
}
