package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;
    private String name;
    private String email;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Address> addresses;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    private String password;
    @Column(name = "userName")
    private String userName;
    private String CI;
    private String status;
    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    private List<Order> orders;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> reviews;

    public User(int userID) {
        this.userID = userID;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", CI='" + CI + '\'' +
                '}';
    }
}
