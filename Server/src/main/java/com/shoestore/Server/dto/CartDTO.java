package com.shoestore.Server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDTO {
  private int cartID;
  private LocalDateTime createAt;
  private List<CartItemDTO> cartItems;
//  private UserDTO user;
}
