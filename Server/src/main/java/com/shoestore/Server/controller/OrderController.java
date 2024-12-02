package com.shoestore.Server.controller;


import com.shoestore.Server.entities.Order;
import com.shoestore.Server.entities.User;
import com.shoestore.Server.service.OrderService;
import com.shoestore.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Order> createBasicOrder(@RequestBody Order order) {
        User user = userService.findById(order.getUser().getUserID());
        order.setUser(user);
        Order newOrder = orderService.addOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getAll(@PathVariable("id") int id) {
        // Lấy danh sách đơn hàng với chi tiết sản phẩm đã được tải
       Order order=orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/update-status")
    public ResponseEntity<?> updateOrderStatus(@RequestBody Map<String, Object> payload) {
//        System.out.println("Payload received: " + payload);
        try {
            // Kiểm tra và chuyển đổi kiểu dữ liệu
            int orderId;
            Object orderIdObj = payload.get("orderId");
            if (orderIdObj instanceof Integer) {
                orderId = (Integer) orderIdObj;
            } else if (orderIdObj instanceof Number) {
                orderId = ((Number) orderIdObj).intValue();
            } else {
                return ResponseEntity.badRequest().body("orderId không hợp lệ!");
            }
            String status = (String) payload.get("status");
            if (status == null || status.isEmpty()) {
                return ResponseEntity.badRequest().body("status không hợp lệ!");
            }
//            System.out.println("OrderId: " + orderId + ", Status: " + status);
            orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok("Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cập nhật thất bại: " + e.getMessage());
        }
    }

    @GetMapping("/revenue")
    public ResponseEntity<Map<String, Object>> getRevenueStatistics(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        // Parse the date strings to LocalDate
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        // Get the revenue statistics from the service
        Map<String, Object> stats = orderService.getRevenueStatistics(start, end);

        // Return the statistics in the response
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/dsachOrders")
    public List<Map<String, Object>> getAllOrders() {
        // Lấy danh sách đơn hàng với chi tiết sản phẩm đã được tải
        List<Order> orders = orderService.findAll();

        return orders.stream()
                .map(order -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("orderID", order.getOrderID());
                    map.put("dateCreated", order.getOrderDate());
                    map.put("name", order.getUser().getName());
                    map.put("totalPrice", order.getTotal());
                    map.put("status", order.getStatus());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/yearly-revenue")
    public ResponseEntity<Map<String, Object>> getYearlyRevenue() {
        Map<String, Object> data = orderService.getRevenueAndQuantityForCurrentYear();
        return ResponseEntity.ok(data);
    }

    // Danh sách khách hàng thân thiết
    @GetMapping("/loyal-customers")
    public ResponseEntity<List<Map<String, Object>>> getTop10LoyalCustomers(
            @RequestParam(value = "minOrders", defaultValue = "3") int minOrders) {
        List<Object[]> loyalCustomers = orderService.getTop10LoyalCustomers(minOrders);

        List<Map<String, Object>> customers = new ArrayList<>();
        for (Object[] customerData : loyalCustomers) {
            Map<String, Object> customer = new HashMap<>();
            User user = (User) customerData[0];
            customer.put("userName", user.getUserName());
            customer.put("name", user.getName());
            customer.put("phone", user.getPhoneNumber());
            customer.put("email", user.getEmail());
            customer.put("orderCount", customerData[1]);
            customer.put("totalSpent", customerData[2]);
            customers.add(customer);
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/OrderStatistics")
    public Map<String, Long> getOrderStatistics() {
        return orderService.getOrderStatistics();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getOrdersByUserId(@PathVariable("userId") int userId) {
        try {
            List<Order> orders = orderService.findByUserId(userId);
            List<Map<String, Object>> result = orders.stream()
                    .map(order -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("orderID", order.getOrderID());
                        map.put("dateCreated", order.getOrderDate());
                        map.put("name", order.getUser().getName());
                        map.put("totalPrice", order.getTotal());
                        map.put("status", order.getStatus());
                        return map;
                    })
                    .collect(Collectors.toList());
            System.out.println(orders);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
