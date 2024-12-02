package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.CartItem;
import com.shoestore.Server.entities.CartItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {

  @Query("SELECT ci " +
          "FROM CartItem ci " +
          "WHERE ci.cart.cartID = :cartId")
  List<CartItem> findCartItemsByCartId(@Param("cartId") int cartId);

}

