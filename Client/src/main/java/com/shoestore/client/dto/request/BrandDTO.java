package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BrandDTO {
    private int brandID;
    private String name;
    private String image;

    public BrandDTO(String image, int brandID) {
        this.image = image;
        this.brandID = brandID;
    }
}
