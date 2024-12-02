package com.shoestore.client.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDTO {
    private int orderID;
    private String status;
    private double totalPrice;
    private String name;
    private String dateCreated;
    private String productName;
    private int quantity;

    public OrderDTO(String dateCreated, double totalPrice, String status, String name, int orderID) {
        this.dateCreated = dateCreated;
        this.totalPrice = totalPrice;
        this.status = status;
        this.name = name;
        this.orderID = orderID;
    }
}

