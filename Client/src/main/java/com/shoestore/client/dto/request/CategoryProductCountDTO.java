package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryProductCountDTO {
    private int categoryID;
    private String name;
    private String description;
    private int productCount;
}

