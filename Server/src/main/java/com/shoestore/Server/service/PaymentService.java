package com.shoestore.Server.service;

import com.shoestore.Server.entities.Payment;

import java.util.List;

public interface PaymentService {
    Payment addPayment(Payment payment);
    Payment getPaymentById(int id);
    Payment getPaymentByOrderId(int id);
    List<Payment> getAll();
}
