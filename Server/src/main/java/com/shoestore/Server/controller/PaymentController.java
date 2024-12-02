package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Order;
import com.shoestore.Server.entities.Payment;
import com.shoestore.Server.entities.User;
import com.shoestore.Server.service.OrderService;
import com.shoestore.Server.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        Order order=orderService.findById(payment.getOrder().getOrderID());
        payment.setOrder(order);
        Payment paymentAdd=paymentService.addPayment(payment);
        return ResponseEntity.ok(paymentAdd);
    }
}
