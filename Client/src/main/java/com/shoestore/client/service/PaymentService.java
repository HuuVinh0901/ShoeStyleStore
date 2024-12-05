package com.shoestore.client.service;

import com.shoestore.client.dto.request.PaymentDTO;
import jakarta.mail.MessagingException;

public interface PaymentService {
   PaymentDTO addPayment(PaymentDTO paymentDTO);
   String sendAmount(long amount) throws MessagingException;
}
