package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("SELECT p FROM Payment p WHERE p.order.orderID = :orderId")
    Payment findPaymentByOrderId(@Param("orderId") int orderId);
}
