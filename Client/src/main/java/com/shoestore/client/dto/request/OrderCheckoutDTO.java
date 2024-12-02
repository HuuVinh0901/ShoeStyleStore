package com.shoestore.client.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCheckoutDTO {
    private int orderID;
    private LocalDate orderDate;
    private String status;
    private double total;
    private double feeShip;
    private VoucherDTO voucher;
    private String shippingAddress;
    private UserDTO user;
}
