package com.shoestore.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.SupplierDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SupplierResponseDTO {
    private int totalItems;
    private int totalPages;
    @JsonProperty("suppliers")
    private List<SupplierDTO> supplierDTOs;
}
