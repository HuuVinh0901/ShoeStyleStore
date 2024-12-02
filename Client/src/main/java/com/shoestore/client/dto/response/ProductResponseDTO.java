package com.shoestore.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shoestore.client.dto.request.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder(alphabetic = false)
@ToString
public class ProductResponseDTO {
    private int totalItems;
    private int totalPages;
    @JsonProperty("products")
    private List<ProductDTO> productDTOs;
}
