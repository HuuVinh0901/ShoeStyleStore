package com.shoestore.client.service;

import com.shoestore.client.dto.request.PaymentDTO;
import com.shoestore.client.dto.response.DataResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
   PaymentDTO addPayment(PaymentDTO paymentDTO);
   String sendAmount(long amount) throws MessagingException;
   boolean checkPaymentStatus(String txnRef);
   PaymentDTO getPaymentByOrderId(int id);

}
