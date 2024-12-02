package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Order;
import com.shoestore.Server.repositories.OrderRepository;
import com.shoestore.Server.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void updateOrderStatus(int orderId, String status) {
        // Tìm đơn hàng theo ID
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy đơn hàng với ID: " + orderId);
        }
        Order order = optionalOrder.get();
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public Order findById(int orderID) {
        return orderRepository.findById(orderID).orElse(null);
    }

    @Override
    public Order addOrder(Order order) {
        if (order.getVoucher() != null && order.getVoucher().getVoucherID() == 0) {
            order.setVoucher(null);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        String jpql = """
        SELECT o 
        FROM Order o
        JOIN FETCH o.user u
        JOIN FETCH o.orderDetails od
        JOIN FETCH od.productDetail pd
        JOIN FETCH pd.product p
        ORDER BY o.orderID DESC
    """;

        return entityManager.createQuery(jpql, Order.class).getResultList();
    }


    public Map<String, Object> getRevenueStatistics(LocalDate startDate, LocalDate endDate) {
        // Thực hiện truy vấn để lấy tổng số đơn hàng, tổng doanh thu và doanh thu đã giảm
        List<Object[]> result = orderRepository.findRevenueAndDiscountedRevenueBetweenDates(startDate, endDate);

        Map<String, Object> data = new HashMap<>();

        if (result != null && !result.isEmpty()) {
            Object[] row = result.get(0);

            data.put("totalOrders", row[0] != null ? row[0] : 0);
            data.put("totalRevenue", row[1] != null ? row[1] : 0);
//            data.put("totalRevenueNotComplete", row[2] != null ? row[2] : 0);
//            data.put("totalRevenue", row[3] != null ? row[3] : 0);
        } else {
            // Nếu không có dữ liệu, gán tất cả giá trị là 0
            data.put("totalOrders", 0);
            data.put("totalRevenue", 0);
//            data.put("totalRevenue", 0);
//            data.put("totalDiscountedRevenue", 0);
        }

        return data;
    }




    @Override
    public Map<String, Object> getRevenueAndQuantityForCurrentYear() {
        int currentYear = LocalDate.now().getYear();
        System.out.println("Current Year: " + currentYear); // In ra năm hiện tại

        List<Object[]> result = orderRepository.findTotalRevenueAndQuantityByYear(currentYear);

        // In ra kết quả từ truy vấn database (các dòng dữ liệu)
        System.out.println("Result from database: " + result);

        Map<String, Object> data = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#,###.##");  // Định dạng số với dấu phân cách hàng nghìn

        if (result != null && !result.isEmpty()) {
            Object[] row = result.get(0);

            // In ra các giá trị lấy từ row
            System.out.println("Total Quantity: " + (row[0] != null ? row[0] : 0));
            System.out.println("Total Revenue: " + (row[1] != null ? df.format(row[1]) : 0));

            data.put("totalQuantity", row[0] != null ? row[0] : 0);
            data.put("totalRevenue", row[1] != null ? row[1] : 0);
        } else {
            System.out.println("No data found.");

            data.put("totalQuantity", 0);
            data.put("totalRevenue", 0);
        }

        // In ra Map kết quả
        System.out.println("Data Map: " + data);

        return data;
    }



    @Override
    public List<Object[]> getTop10LoyalCustomers(int minOrders) {
        List<Object[]> loyalCustomers = orderRepository.findLoyalCustomers(minOrders);
        return loyalCustomers.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getOrderStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("Processing", orderRepository.countByStatus("Processing"));
        statistics.put("Shipped", orderRepository.countByStatus("Shipped"));
        statistics.put("Delivered", orderRepository.countByStatus("Delivered"));
        statistics.put("Return", orderRepository.countByStatus("Return"));
        return statistics;
    }


    @Override
    public List<Order> findByUserId(int userId) {
        return orderRepository.findByUser_UserID(userId);
    }

}
