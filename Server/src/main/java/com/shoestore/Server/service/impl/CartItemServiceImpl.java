package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.entities.CartItem;
import com.shoestore.Server.entities.CartItemKey;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.repositories.CartItemRepository;
import com.shoestore.Server.repositories.CartRepository;
import com.shoestore.Server.repositories.ProductDetailRepository;
import com.shoestore.Server.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final ProductDetailRepository productDetailRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public CartItemServiceImpl(ProductDetailRepository productDetailRepository, CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.productDetailRepository = productDetailRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {
        return cartItemRepository.findCartItemsByCartId(cartId);
    }

    public CartItem addCartItem(CartItem cartItem) {
        Cart cart = cartRepository.findById(cartItem.getId().getCartId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        ProductDetail productDetail = productDetailRepository.findById(cartItem.getId().getProductDetailId())
                .orElseThrow(() -> new IllegalArgumentException("ProductDetail not found"));
        // Khởi tạo CartItemKey
        CartItemKey cartItemKey = new CartItemKey(cart.getCartID(), productDetail.getProductDetailID());
        cartItem.setId(cartItemKey);

        // Gán lại đối tượng liên quan
        cartItem.setCart(cart);
        cartItem.setProductDetail(productDetail);
        System.out.println(cartItem);
        // Lưu CartItem
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItemById(CartItemKey cartItemKey) {
        return cartItemRepository.findById(cartItemKey).orElse(null);
    }

    @Override
    public CartItem updateQuantity(CartItemKey id, CartItem cartItem) {
        Optional<CartItem> existCartItem= cartItemRepository.findById(id);
        if(existCartItem.isPresent()){
            CartItem entityCartItem=existCartItem.get();

            entityCartItem.setQuantity(cartItem.getQuantity());
            entityCartItem.setCart(cartItem.getCart());
            entityCartItem.setProductDetail(cartItem.getProductDetail());
            return cartItemRepository.save(entityCartItem);
        }
        return null;
    }

    @Override
    public void deleteCartItem(CartItemKey id) {
        cartItemRepository.deleteById(id);
    }


}


