package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Cart;
import com.shoestore.Server.entities.Order;
import com.shoestore.Server.entities.Payment;
import com.shoestore.Server.entities.User;
import com.shoestore.Server.service.OrderService;
import com.shoestore.Server.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/orderId/{id}")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable int id) {
        Payment payment=paymentService.getPaymentByOrderId(id);
        return ResponseEntity.ok(payment);
    }
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllPayment() {
        List<Payment> payment=paymentService.getAll();
        Map<String,Object> response= new HashMap<>();
        response.put("payment",payment);
        return ResponseEntity.ok(response);

    }
}
