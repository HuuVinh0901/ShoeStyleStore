package com.shoestore.Server.service.impl;

import com.shoestore.Server.dto.OrderDetailDTO;
import com.shoestore.Server.entities.*;
import com.shoestore.Server.repositories.OrderDetailRepository;
import com.shoestore.Server.repositories.OrderRepository;
import com.shoestore.Server.repositories.ProductDetailRepository;
import com.shoestore.Server.repositories.ProductRepository;
import com.shoestore.Server.service.OrderDetailService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductDetailRepository productDetailRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository, ProductDetailRepository productDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.productDetailRepository = productDetailRepository;
    }


    public Map<String, Object> fetchOrderDetailByOrderID(Long orderID) {
        // Lệnh Native SQL để lấy thông tin đơn hàng và chi tiết sản phẩm
//        String sql = """
//            SELECT o.status, o.feeShip, o.shippingAddress, v.discountValue AS voucherDiscount, v.discountType AS voucherType,
//                   od.quantity, od.price, p.productName, u.name AS userName, u.phoneNumber, a.street AS userAddress, o.orderDate, pd.productID, u.email, pd.imageURL
//            FROM OrderDetail od
//            JOIN ProductDetail pd ON od.productDetailID = pd.productDetailID
//            JOIN Product p ON pd.productID = p.productID
//            JOIN Orders o ON od.orderID = o.orderID
//            LEFT JOIN Voucher v ON o.voucherID = v.voucherID
//            LEFT JOIN Users u ON o.userID = u.userID
//            LEFT JOIN Address a ON u.userID = a.userID
//            WHERE o.orderID = ?1
//        """;
        String sql = """
             SELECT o.status, o.feeShip, o.shippingAddress, v.discountValue AS voucherDiscount, v.discountType AS voucherType,
                             od.quantity, od.price, p.productName, u.name AS userName, u.phoneNumber, a.street AS userAddress, o.orderDate,
                             pd.productID, u.email,
                             (SELECT TOP 1 pi.imageURL
                     FROM Product_ImageURL pi
                     WHERE pi.productID = p.productID) AS imageURL,
                             pa.status AS paymentStatus
                     FROM OrderDetail od
                     JOIN ProductDetail pd ON od.productDetailID = pd.productDetailID
                     JOIN Product p ON pd.productID = p.productID
                     JOIN Orders o ON od.orderID = o.orderID
                     LEFT JOIN Voucher v ON o.voucherID = v.voucherID
                     LEFT JOIN Users u ON o.userID = u.userID
                     LEFT JOIN Address a ON u.userID = a.userID
                     LEFT JOIN Payment pa ON o.orderID = pa.orderID
                     WHERE o.orderID = ?1
        """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, orderID);

        // Lấy kết quả từ cơ sở dữ liệu
        List<Object[]> results = query.getResultList();

        // Biến để lưu thông tin trả về
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> orderDetails = new ArrayList<>();
        Map<String, Object> orderInfo = new HashMap<>();

        // Biến để tính toán tổng giá trị sản phẩm
        double totalProductValue = 0.0;

        // Lặp qua kết quả truy vấn để xử lý
        for (Object[] result : results) {
            if (orderInfo.isEmpty()) {
                // Lấy thông tin đơn hàng (chỉ cần lấy 1 lần)
                double voucherDiscount = Optional.ofNullable(result[3]).map(v -> (double) v).orElse(0.0); // Giá trị giảm giá, mặc định 0.0 nếu NULL
                String voucherType = Optional.ofNullable(result[4]).map(v -> (String) v).orElse("fixed"); // Loại giảm giá, mặc định "fixed" nếu NULL
                double feeShip = Optional.ofNullable(result[1]).map(f -> (double) f).orElse(0.0); // Phí vận chuyển, mặc định 0.0 nếu NULL

                // Lưu thông tin đơn hàng vào map
                orderInfo.put("status", result[0]);
                orderInfo.put("feeShip", feeShip);
                orderInfo.put("shippingAddress", result[2]);
                orderInfo.put("voucher", voucherDiscount);
                orderInfo.put("voucherType", voucherType);
                orderInfo.put("paymentStatus", result[15]); // Thêm trạng thái thanh toán
            }

            // Lấy thông tin sản phẩm
            int quantity = Optional.ofNullable(result[5]).map(q -> (int) q).orElse(0);
            double price = Optional.ofNullable(result[6]).map(p -> (double) p).orElse(0.0);
            String productName = (String) result[7];
            String userName = (String) result[8];
            String phoneNumber = (String) result[9];
            String addressStr = (String) result[10];
            java.sql.Date sqlDate = (java.sql.Date) result[11];
            int productID = (int) result[12];
            String email = (String) result[13];
            String imageUrl = (String) result[14]; // Cột 14 là imageURL trong truy vấn đã sửa

            LocalDate orderDate = sqlDate.toLocalDate();

            totalProductValue += quantity * price;

            // Thêm thông tin sản phẩm vào danh sách
            Map<String, Object> detail = new HashMap<>();
            detail.put("productName", productName);
            detail.put("quantity", quantity);
            detail.put("price", price);
            detail.put("productID", productID);

            if (imageUrl != null) {
                detail.put("imageURL", imageUrl);
            }

            orderDetails.add(detail);
            response.put("userName", userName);
            response.put("phoneNumber", phoneNumber);
            response.put("address", addressStr);
            response.put("orderDate", orderDate);
            response.put("email", email);
        }



        // Tính toán tổng tiền
        double voucherDiscount = Optional.ofNullable(orderInfo.get("voucher")).map(v -> (double) v).orElse(0.0); // Giá trị giảm giá
        String voucherType = Optional.ofNullable(orderInfo.get("voucherType")).map(v -> (String) v).orElse("fixed"); // Loại giảm giá
        double feeShip = Optional.ofNullable(orderInfo.get("feeShip")).map(f -> (double) f).orElse(0.0); // Phí vận chuyển

        // Biến để tính số tiền giảm giá
        double discountAmount = 0.0;
        if (voucherDiscount > 0) {
            // Tính số tiền giảm giá
            if ("percentage".equalsIgnoreCase(voucherType)) {
                discountAmount = totalProductValue * (voucherDiscount / 100);
            } else {
                discountAmount = voucherDiscount;
            }
        } else {
            discountAmount = 0.0;
        }

        double totalAmount = totalProductValue - discountAmount + feeShip;

        response.put("orderID", orderID);
        response.put("orderDetails", orderDetails);
        response.putAll(orderInfo);
        response.put("totalAmount", totalAmount);
        response.put("paymentStatus", orderInfo.get("paymentStatus"));
        // Trả về kết quả
        return response;
    }

    @Override
    public List<Product> getTopSellingProducts(LocalDate startDate, LocalDate endDate, int limit) {
        List<Object[]> results = orderDetailRepository.findTopSellingProducts(startDate, endDate);

        // Chỉ lấy sản phẩm bán chạy nhất (giới hạn số lượng sản phẩm)
        return results.stream()
                .limit(limit)
                .map(obj -> (Product) obj[0])
                .collect(Collectors.toList());
    }


    @Override
    public List<OrderDetail> findByProductIDAndOrderID(int productID, int orderID) {
        return orderDetailRepository.findByProductIDAndOrderID(productID, orderID);
    }
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
//    @Override
//    public Optional<OrderDetail> findByProductIDAndOrderIDDelete(int productID, int orderID) {
//        return orderDetailRepository.findByProductProductIDAndOrderOrderID(productID, orderID);
//    }
//
//    @Transactional
//    @Override
//    public void deleteByProductIDAndOrderID(int productID, int orderID) {
//        orderDetailRepository.deleteByProductProductIDAndOrderOrderID(productID, orderID);
//    }

    public OrderDetail addProductToOrder(OrderDetailDTO orderDetailDTO) {
        // Lấy Order hiện tại
        Order order = orderRepository.findById(orderDetailDTO.getOrderID())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Tìm hoặc tạo ProductDetail từ DTO
        ProductDetail productDetail = productDetailRepository.findByProductIdAndColorAndSize(
                        orderDetailDTO.getProductID(),
                        orderDetailDTO.getColor(),
                        orderDetailDTO.getSize())
                .orElseGet(() -> {
                    Product product = productRepository.findById(orderDetailDTO.getProductID())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    ProductDetail newProductDetail = new ProductDetail();
                    newProductDetail.setProduct(product);
                    newProductDetail.setColor(orderDetailDTO.getColor());
                    newProductDetail.setSize(orderDetailDTO.getSize());
                    newProductDetail.setStockQuantity(10); // Hoặc lấy từ dữ liệu khác
                    productDetailRepository.save(newProductDetail);
                    return newProductDetail;
                });

        // Kiểm tra số lượng tồn kho
        if (productDetail.getStockQuantity() < orderDetailDTO.getQuantity()) {
            throw new RuntimeException("Not enough stock for this product");
        }

        // Tạo OrderDetail mới
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProductDetail(productDetail);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setPrice(orderDetailDTO.getPrice());

        // Giảm số lượng tồn kho
        productDetail.setStockQuantity(productDetail.getStockQuantity() - orderDetailDTO.getQuantity());
        productDetailRepository.save(productDetail);

        // Lưu OrderDetail
        return orderDetailRepository.save(orderDetail);
    }


}


