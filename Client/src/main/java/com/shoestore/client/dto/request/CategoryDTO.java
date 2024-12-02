package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO {
    private int categoryID;
    private String name;
    private String description;
}
