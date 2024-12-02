package com.shoestore.Server.service;

import com.shoestore.Server.entities.Payment;

public interface PaymentService {
    Payment addPayment(Payment payment);
    Payment getPaymentById(int id);
}
