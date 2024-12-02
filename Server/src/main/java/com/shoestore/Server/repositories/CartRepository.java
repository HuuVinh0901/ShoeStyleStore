package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query("SELECT c " +
            "FROM Cart c " +
            "WHERE c.user.userID = :userId")
    Cart findCartByUserId(@Param("userId") int userId);
}
