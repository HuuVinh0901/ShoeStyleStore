package com.shoestore.Server.service;

import com.shoestore.Server.dto.OrderDetailDTO;
import com.shoestore.Server.entities.OrderDetail;
import com.shoestore.Server.entities.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderDetailService {
    public Map<String, Object> fetchOrderDetailByOrderID(Long orderID);
    public List<Product> getTopSellingProducts(LocalDate startDate, LocalDate endDate, int limit);
    public OrderDetail save(OrderDetail orderDetail);

    public List<OrderDetail> findByProductIDAndOrderID(int productID, int orderID) ;
//    public Optional<OrderDetail> findByProductIDAndOrderIDDelete(int productID, int orderID) ;
//    public void deleteByProductIDAndOrderID(int productID, int orderID) ;


}
