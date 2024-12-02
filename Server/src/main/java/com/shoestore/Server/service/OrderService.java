package com.shoestore.Server.service;

import com.shoestore.Server.entities.Order;
import com.shoestore.Server.entities.OrderDetail;
import com.shoestore.Server.entities.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {
    public List<Order> findAll();
    public Map<String, Object> getRevenueStatistics(LocalDate startDate, LocalDate endDate);
    public Map<String, Object> getRevenueAndQuantityForCurrentYear();
    public List<Object[]> getTop10LoyalCustomers(int minOrders);
    public Map<String, Long> getOrderStatistics();
    public void updateOrderStatus(int orderId, String status) ;
    Order findById(int orderID);
    Order addOrder(Order order);
    List<Order> findByUserId(int userId);

}
