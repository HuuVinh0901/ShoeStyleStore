package com.shoestore.client.service;

import com.shoestore.client.dto.request.OrderCheckoutDTO;
import com.shoestore.client.dto.request.OrderDTO;
import com.shoestore.client.dto.request.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    public List<OrderDTO> getOrdersFromServer();
    public Page<OrderDTO> getOrdersWithPagination(Pageable pageable);
    public Map<String, Object> getRevenueStatistics(String startDate, String endDate);
    public List<ProductDTO> getTopSellingProducts(String type);
    public Map<String, Object> getYearlyRevenue();
    public List<Map<String, Object>> getTop10LoyalCustomers();
    public Map<String, Long> getOrderStatistics();
    OrderCheckoutDTO addOrder(OrderCheckoutDTO orderCheckoutDTO);
    OrderCheckoutDTO getById(int id);
    List<OrderDTO> getOrdersByUserId(int userId);
}
