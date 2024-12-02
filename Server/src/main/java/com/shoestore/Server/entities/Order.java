package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;
    private LocalDate orderDate;
    private String status;
    private double total;
    private double feeShip;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voucherID",nullable = true)
    @JsonIgnore
    private Voucher voucher;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetail> orderDetails;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private Payment payment;

    private String shippingAddress;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public Order(int orderID){
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", total=" + total +
                ", feeShip=" + feeShip +
                ", user=" + user +
                '}';
    }
}
