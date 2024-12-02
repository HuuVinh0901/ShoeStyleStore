package com.shoestore.Server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartItemDTO {
  private IdDTO id;
  private ProductDTO product;
  @JsonProperty("productDetails")
  private List<ProductDetailDTO> productDetails;
  private int quantity;
  private double subTotal;
  @Data
  public static class IdDTO {
    private int cartId;
    private int productId;
  }
}
