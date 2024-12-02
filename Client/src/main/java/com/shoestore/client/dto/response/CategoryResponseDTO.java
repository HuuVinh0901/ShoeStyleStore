package com.shoestore.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoestore.client.dto.request.BrandDTO;
import com.shoestore.client.dto.request.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDTO {
    private int totalItems;
    private int totalPages;
    @JsonProperty("categories")
    private List<CategoryDTO> categoryDTOs;

}
