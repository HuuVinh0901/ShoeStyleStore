package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandID")
    private int brandID;
    private String name;
    private String image;
    @OneToMany(mappedBy = "brand")
    @JsonBackReference("brandReference")
    private List<Product> products;

    public Brand() {
    }
    public Brand(int brandID) {
        this.brandID = brandID;
    }

}
