package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VoucherDTO {
    private int voucherID;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String discountType;
    private double discountValue;
    private String status;
    private double minValueOrder;
    private List<OrderDTO> orders;


}


