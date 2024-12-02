package com.shoestore.Server.dto;

import com.shoestore.Server.entities.Color;
import com.shoestore.Server.entities.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private int orderID;
    private int productID;
    private Color color;
    private Size size;
    private int quantity;
    private double price;
}
