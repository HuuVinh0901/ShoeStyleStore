package com.shoestore.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductDetailDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailResponseDTO {
    private int totalItems;
    private int totalPages;
    @JsonProperty("productDetails")
    private List<ProductDetailDTO> productDetailDTOs;
}
