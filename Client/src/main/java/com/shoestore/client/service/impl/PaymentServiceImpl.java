package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.OrderCheckoutDTO;
import com.shoestore.client.dto.request.PaymentDTO;
import com.shoestore.client.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        String apiUrl = "http://localhost:8080/payment/add";
        ResponseEntity<PaymentDTO> response=restTemplate.postForEntity(
                apiUrl,paymentDTO, PaymentDTO.class
        );
        System.out.println("Payment Gui: " + response.getBody());
        return response.getBody();
    }
}
