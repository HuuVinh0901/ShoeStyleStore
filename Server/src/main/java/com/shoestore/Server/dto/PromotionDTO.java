package com.shoestore.Server.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PromotionDTO {
  private int promotionID;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
}
