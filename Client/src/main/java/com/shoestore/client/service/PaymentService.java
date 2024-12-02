package com.shoestore.client.service;

import com.shoestore.client.dto.request.PaymentDTO;

public interface PaymentService {
   PaymentDTO addPayment(PaymentDTO paymentDTO);
}
