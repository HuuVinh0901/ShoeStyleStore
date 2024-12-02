package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int cartID;
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> cartItems;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    @JsonBackReference
    private User user;

    public Cart(int cartID) {
        this.cartID = cartID;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", createAt=" + createAt +
                '}';
    }
}
