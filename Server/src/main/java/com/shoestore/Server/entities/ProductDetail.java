package com.shoestore.Server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@ToString
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productDetailID")
    private int productDetailID;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column(name = "stockQuantity", nullable = false)
    @DecimalMin(value = "0", inclusive = false, message = "Số lượng phải lớn hơn 0")
    private int stockQuantity;
    @ManyToOne
    @JoinColumn(name = "productID")
    @JsonBackReference("productDetailsReference")
    private Product product;
    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems;
    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    public ProductDetail(int productDetailID) {
        this.productDetailID = productDetailID;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "productDetailID=" + productDetailID +
                ", color=" + color +
                ", size=" + size +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
