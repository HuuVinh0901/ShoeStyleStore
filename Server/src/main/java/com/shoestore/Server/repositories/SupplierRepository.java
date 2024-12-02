package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findBySupplierID(int id);
}
