package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "Address")
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressID")
    private int addressID;
    private String street;
    private String city;
    private String ward;
    private String district;
    @ManyToOne
    @JoinColumn(name = "userID")
    @JsonBackReference
    private User user;

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", ward='" + ward + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
