package com.shoestore.Server.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class CartItemKey implements Serializable {
    private int cartId;
    private int productDetailId;

    public CartItemKey(int cartId, int productDetailId) { // Đúng thứ tự
        this.cartId = cartId;
        this.productDetailId = productDetailId;
    }
}

