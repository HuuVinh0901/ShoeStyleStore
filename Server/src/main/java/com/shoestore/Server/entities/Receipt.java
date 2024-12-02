package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receiptID")
    private int receiptID;
    private double total;
    private LocalDate receiptDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentID")
    private Payment payment;
}
