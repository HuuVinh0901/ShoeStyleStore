package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Address;
import com.shoestore.Server.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    @Query("SELECT a " +
            "FROM Address a " +
            "WHERE a.user.userID = :userId")
    List<Address> findAddressByUserId(@Param("userId") int userId);
}
