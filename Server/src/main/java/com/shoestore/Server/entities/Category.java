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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryID")
    private int categoryID;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonBackReference("categoryReference")
    private List<Product> products;

    public Category() {
    }
    public Category(int categoryID) {
        this.categoryID = categoryID;
    }
}
