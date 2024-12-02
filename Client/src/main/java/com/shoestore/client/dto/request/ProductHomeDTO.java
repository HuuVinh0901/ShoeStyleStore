package com.shoestore.client.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductHomeDTO {
  private int productID;
  private String productName;
  private List<String> imageURL;
  private double price;
  private String status;
  private String description;
  private String brandName;
  private String categoryName;
  private LocalDateTime createDate;
  private long totalQuantity;
}

