package com.shoestore.client.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CategoryProductCountResponeDTO {
    private int totalItems;
    private int totalPages;
    private List<CartItemResponseDTO> items;
}
