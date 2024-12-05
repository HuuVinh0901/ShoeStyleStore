package com.shoestore.client.dto.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PaymentDTO {
    private int paymentID;
    private OrderCheckoutDTO order;
    private LocalDate paymentDate;
    private String status;
    private String code;
}
