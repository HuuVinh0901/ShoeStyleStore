package com.shoestore.client.dto.response;

import com.shoestore.client.dto.request.ColorDTO;
import com.shoestore.client.dto.request.SizeDTO;
import lombok.Data;

import java.util.List;

@Data
public class CartItemResponseDTO {
  private IdDTO id;
  private int quantity;
  private double subTotal;
  private String productName;
  private List<String> productImage;
  private double productPrice;
  private ColorDTO color;
  private SizeDTO size;
  private int stockQuantity;
  private int productId;
  @Data
  public static class IdDTO {
    private int cartId;
    private int productDetailId;
  }
}
