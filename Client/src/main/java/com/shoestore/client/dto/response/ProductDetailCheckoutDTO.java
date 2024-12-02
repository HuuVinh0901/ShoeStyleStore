package com.shoestore.client.dto.response;

import com.shoestore.client.dto.request.ProductDetailDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDetailCheckoutDTO {
    private ProductDetailDTO productDetailDTO;
    private int quantity;
    private String name;
    private List<String> image;
    private double price;
    private int productId;

    public ProductDetailCheckoutDTO(ProductDetailDTO productDetailDTO, int quantity, String name, List<String> image, double price, int productId) {
        this.productDetailDTO = productDetailDTO;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
        this.price = price;
        this.productId = productId;
    }
}
