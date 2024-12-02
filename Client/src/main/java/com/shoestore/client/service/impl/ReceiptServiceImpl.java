package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.ReceiptDTO;
import com.shoestore.client.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ReceiptDTO addReceipt(ReceiptDTO receiptDTO) {
        String apiUrl = "http://localhost:8080/receipt/add";
        ResponseEntity<ReceiptDTO> response=restTemplate.postForEntity(
                apiUrl,receiptDTO, ReceiptDTO.class
        );
        return response.getBody();
    }
}
