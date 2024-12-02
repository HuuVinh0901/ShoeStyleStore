package com.shoestore.Server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
  private int productID;
  private String productName;
  private double price;
  private String status;
  private String description;
  private String brandName;
  private String categoryName;
  private LocalDateTime createDate;
  private List<String> imageURL;
  private long totalQuantitySold;

  public ProductDTO(String brandName, String categoryName, LocalDateTime createDate, String description, double price, int productID, String productName, String status, long totalQuantitySold) {
    this.brandName = brandName;
    this.categoryName = categoryName;
    this.createDate = createDate;
    this.description = description;
    this.price = price;
    this.productID = productID;
    this.productName = productName;
    this.status = status;
    this.totalQuantitySold = totalQuantitySold;
  }
  public ProductDTO(int productID, String productName, double price, String status,
                    String brandName, String categoryName, String description, LocalDateTime createDate) {
    this.productID = productID;
    this.productName = productName;
    this.price = price;
    this.status = status;
    this.brandName = brandName;
    this.categoryName = categoryName;
    this.description = description;
    this.createDate = createDate;
  }

  public ProductDTO(int productID, String productName, double price, String status,
                    String brandName, String categoryName, String description,
                    LocalDateTime createDate, long totalQuantitySold) {
    this.productID = productID;
    this.productName = productName;
    this.price = price;
    this.status = status;
    this.brandName = brandName;
    this.categoryName = categoryName;
    this.description = description;
    this.createDate = createDate;
    this.totalQuantitySold = totalQuantitySold;
  }
}

