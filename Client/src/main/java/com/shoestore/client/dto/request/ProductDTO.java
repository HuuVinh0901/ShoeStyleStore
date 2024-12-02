package com.shoestore.client.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDTO {
    private int productID;
    private String productName;
    private List<String> imageURL;
    private String description;
    private double price;
    private String status;
    private BrandDTO brand;
    private CategoryDTO category;
    private SupplierDTO supplier;
    private List<ProductDetailDTO> productDetails;
    private LocalDateTime createDate;
//    private PromotionDTO promotionDTO;
}
