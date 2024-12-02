package com.shoestore.client.dto.request;

import com.shoestore.client.dto.response.CartItemResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDTO {
  private int cartID;
//  private LocalDateTime createAt;
//  private List<CartItemResponseDTO> cartItems;
//  private UserDTO user;

  public CartDTO(int cartID) {
    this.cartID = cartID;
  }
}
