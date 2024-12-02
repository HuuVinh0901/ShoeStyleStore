package com.shoestore.Server.controller;

import com.shoestore.Server.dto.OrderDetailDTO;
import com.shoestore.Server.entities.Order;
import com.shoestore.Server.entities.OrderDetail;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.service.OrderDetailService;
import com.shoestore.Server.service.OrderService;
import com.shoestore.Server.service.ProductDetailService;
import com.shoestore.Server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/OrderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderDetail> addProductDetail(@RequestBody OrderDetail orderDetail) {
        System.out.println(orderDetail);
        Order order=orderService.findById(orderDetail.getOrder().getOrderID());
        ProductDetail productDetail=productDetailService.getProductDetailById(orderDetail.getProductDetail().getProductDetailID());
        System.out.println(order);
        System.out.println(productDetail);
        orderDetail.setOrder(order);
        orderDetail.setProductDetail(productDetail);
        OrderDetail orderDetailAdd=orderDetailService.save(orderDetail);
        return ResponseEntity.ok(orderDetailAdd);
    }
    @GetMapping("/top-selling")
//    http://localhost:8080/OrderDetail/top-selling?type=day&limit=5
    public ResponseEntity<List<Product>> getTopSellingProducts(
            @RequestParam String type,
            @RequestParam(required = false, defaultValue = "5") int limit) {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();
        System.out.println(endDate);
        switch (type) {
            case "day":
                startDate = endDate.minusDays(1);
                break;
            case "week":
                startDate = endDate.minusWeeks(1);
                break;
            case "month":
                startDate = endDate.minusMonths(1);
                break;
            case "year":
                startDate = endDate.minusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        List<Product> products = orderDetailService.getTopSellingProducts(startDate, endDate, limit);
        return ResponseEntity.ok(products);
    }

    // API lấy thông tin chi tiết đơn hàng theo orderID
    @GetMapping("/layTT/{orderID}")
    public ResponseEntity<Map<String, Object>> getOrderDetailByOrderID(@PathVariable Long orderID) {
        Map<String, Object> orderDetail = orderDetailService.fetchOrderDetailByOrderID(orderID);
        return ResponseEntity.ok(orderDetail);
    }


//
//
//    // Lấy danh sách sản phẩm chưa có trong OrderDetail
//    @GetMapping("/addProductToOrder/{orderID}")
//    public ResponseEntity<List<Product>> getAvailableProducts(@PathVariable int orderID) {
//        List<Product> availableProducts = productService.getProductsNotInOrderDetail(orderID);
//        return ResponseEntity.ok(availableProducts);
//    }
//
//    @PostMapping("/updateQuantity")
//    public ResponseEntity<?> updateQuantity(@RequestBody Map<String, Object> requestData) {
//        try {
//            // Lấy giá trị từ requestData
//            int productID = Integer.parseInt(requestData.get("productID").toString());
//            int quantity = Integer.parseInt(requestData.get("quantity").toString());
//            int orderID = Integer.parseInt(requestData.get("orderID").toString());  // Lấy orderID từ request
//
//            // Tìm OrderDetail theo productID và orderID
//            List<OrderDetail> orderDetails = orderDetailService.findByProductIDAndOrderID(productID, orderID);
//
//            if (orderDetails.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Map.of("success", false, "message", "Sản phẩm không tồn tại trong OrderDetail"));
//            }
//
//            // Cập nhật số lượng cho từng OrderDetail
//            for (OrderDetail orderDetail : orderDetails) {
//                orderDetail.setQuantity(quantity);
//                orderDetailService.save(orderDetail);  // Lưu lại OrderDetail sau khi cập nhật
//            }
//
//            return ResponseEntity.ok(Map.of("success", true, "message", "Cập nhật số lượng thành công"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "message", "Có lỗi xảy ra khi cập nhật số lượng"));
//        }
//    }
//
////    @DeleteMapping("/deleteProduct/{productID}")
////    public ResponseEntity<?> deleteProduct(@PathVariable int productID, @RequestParam int orderID) {
////        try {
////            // Tìm OrderDetail theo productID và orderID
////            Optional<OrderDetail> orderDetail = orderDetailService.findByProductIDAndOrderIDDelete(productID, orderID);
////
////            if (!orderDetail.isPresent()) {
////                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
////                        .body(Map.of("success", false, "message", "Sản phẩm không tồn tại trong OrderDetail"));
////            }
////
////            // Xóa sản phẩm
////            orderDetailService.deleteByProductIDAndOrderID(productID, orderID);
////
////            return ResponseEntity.ok(Map.of("success", true, "message", "Xóa sản phẩm thành công"));
////        } catch (Exception e) {
////            e.printStackTrace();  // Debug để kiểm tra lỗi chi tiết
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
////                    .body(Map.of("success", false, "message", "Có lỗi xảy ra khi xóa sản phẩm"));
////        }
////    }
}
