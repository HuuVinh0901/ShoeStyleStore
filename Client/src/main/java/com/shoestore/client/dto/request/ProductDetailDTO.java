package com.shoestore.client.dto.request;



/*
    @author: Đào Thanh Phú
    Date: 11/22/2024
    Time: 3:09 PM
    ProjectName: Client
*/


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDetailDTO {
    private int productDetailID;
    private ColorDTO color;
    private SizeDTO size;
    private int stockQuantity;
//    private ProductDTO product;

    public ProductDetailDTO(int productDetailID) {
        this.productDetailID = productDetailID;
    }
}
