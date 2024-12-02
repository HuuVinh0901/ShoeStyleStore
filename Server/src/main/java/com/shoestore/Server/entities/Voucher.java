package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
@ToString
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucherID")
    private int voucherID;
    private String name;
    private String description;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "discountType")
    private String discountType;
    @Column(name = "discountValue")
    private double discountValue;
    private String status;
    private double minValueOrder;
    @OneToMany( mappedBy = "voucher",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

    public Voucher(int voucherID, String name, LocalDate startDate, String description, LocalDate endDate, String discountType, double discountValue, String status, double minValueOrder) {
        this.voucherID = voucherID;
        this.name = name;
        this.startDate = startDate;
        this.description = description;
        this.endDate = endDate;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.status = status;
        this.minValueOrder = minValueOrder;
    }

}
