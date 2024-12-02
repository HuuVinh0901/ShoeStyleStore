package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Integer> {
}
