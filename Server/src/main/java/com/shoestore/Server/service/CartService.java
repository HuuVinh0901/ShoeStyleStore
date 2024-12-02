package com.shoestore.Server.service;

import com.shoestore.Server.entities.Cart;

public interface CartService {
    Cart getCartByUserId(int id);
}
