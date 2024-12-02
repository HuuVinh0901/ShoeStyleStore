package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.repositories.CartRepository;
import com.shoestore.Server.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCartByUserId(int id) {
        return cartRepository.findCartByUserId(id);
    }
}
