package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID")
    private int roleID;
    private String name;
    private String description;
    @OneToMany(mappedBy = "role")
    @JsonBackReference
    @ToString.Exclude
    private List<User> users;
}
