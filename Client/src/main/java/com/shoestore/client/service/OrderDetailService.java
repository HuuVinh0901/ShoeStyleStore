package com.shoestore.client.service;

import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.response.OrderDetailResponeDTO;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {

    // lấy API từ server
    public Map<String, Object> fetchOrderDetailByOrderID(int orderID);
    public List<ProductDTO> getAvailableProducts(int orderID);
    OrderDetailResponeDTO addOrderDetail(OrderDetailResponeDTO orderDetailResponeDTO);
}
