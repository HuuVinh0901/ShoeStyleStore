package com.shoestore.Server.controller;

import com.shoestore.Server.entities.CartItem;
import com.shoestore.Server.entities.Payment;
import com.shoestore.Server.entities.Receipt;
import com.shoestore.Server.service.PaymentService;
import com.shoestore.Server.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final PaymentService paymentService;

    public ReceiptController(ReceiptService receiptService, PaymentService paymentService) {
        this.receiptService = receiptService;
        this.paymentService = paymentService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addReceipt(@RequestBody Receipt receipt) {
        Payment payment=paymentService.getPaymentById(receipt.getPayment().getPaymentID());
        System.out.println(payment);
        receipt.setPayment(payment);
        System.out.println(receipt);
        try {
            Receipt receiptAdd=receiptService.addReceipt(receipt);
            return ResponseEntity.ok(receiptAdd);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
