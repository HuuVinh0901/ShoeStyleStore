package com.shoestore.client.dto.request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PromotionDTO {
    private int promotionID;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String promotionType;
    private String promotionValue;
}
