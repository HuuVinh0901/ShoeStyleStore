package com.shoestore.Server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotionID")
    private int promotionID;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name = "promotionType")
    private String promotionType;
    @Column(name = "promotionValue")
    private String promotionValue;
    @OneToMany(mappedBy = "promotion")
    private List<Product> products;
}
