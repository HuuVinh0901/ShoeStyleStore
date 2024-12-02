package com.shoestore.client.dto.response;

import com.shoestore.client.dto.request.OrderDTO;
import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.request.ProductDetailDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private String orderID;
    private String status;
    private double feeShip;
    private String voucher;
    private double totalAmount;
    private String email;
    private List<ProductDTO> orderDetails;
}
