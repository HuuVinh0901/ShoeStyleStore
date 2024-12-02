package com.shoestore.client.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReceiptDTO {
    private int receiptID;
    private double total;
    private LocalDate receiptDate;
    private PaymentDTO payment;
}
