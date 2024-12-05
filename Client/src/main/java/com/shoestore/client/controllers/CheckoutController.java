package com.shoestore.client.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoestore.client.dto.request.*;
import com.shoestore.client.dto.response.*;
import com.shoestore.client.service.*;
import jakarta.mail.MessagingException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CheckoutController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private HttpSession session;

    @GetMapping("/checkout")
    public String showFormCheckoutFromCart(
            @RequestParam("id") List<Integer> productDetailId,
            @RequestParam("quantity") List<Integer> quantities,
            @RequestParam("delivery") int delivery,
            @RequestParam("subToTal") int subTotal,
            @RequestParam("total") int total
            , Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Lấy thông tin user từ session
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Nếu không có user trong session, chuyển hướng về trang đăng nhập
        }

        // Dùng userId để lấy giỏ hàng của người dùng
        int userId = user.getUserID();
        System.out.println(user);

        List<AddressDTO> addressDTOS = addressService.getAddressByUserId(userId);
        System.out.println(addressDTOS);

        // Map sản phẩm với số lượng
        Map<Integer, Integer> selectedProducts = productDetailId.stream()
                .collect(Collectors.toMap(id -> id, id -> quantities.get(productDetailId.indexOf(id))));

        List<ProductDetailCheckoutDTO> productDetailCheckoutDTOS = selectedProducts.entrySet().stream()
                .map(entry -> {
                    Integer productDetailsId = entry.getKey();
                    Integer quantity = entry.getValue();

                    // Tìm ProductDetail theo ID
                    ProductDetailDTO productDetail = productDetailService.getProductDetailById(productDetailsId);
                    ProductDTO productDTO = productService.getProductByProductDetail(productDetailsId);
                    // Tạo DTO chứa thông tin cần thiết
                    return new ProductDetailCheckoutDTO(productDetail, quantity, productDTO.getProductName(), productDTO.getImageURL(), productDTO.getPrice(),productDTO.getProductID());
                })
                .toList();
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        model.addAttribute("user", user);
        model.addAttribute("address", addressDTOS);
        model.addAttribute("ProductDetailCheckoutDTO", productDetailCheckoutDTOS);
        model.addAttribute("subTotal", formatter.format(subTotal) + " VNĐ");
        model.addAttribute("delivery", delivery == 0 ? "FREE" : formatter.format(delivery) + " VNĐ");
        model.addAttribute("total", formatter.format(total));
        // Trả về thông báo thành công
        return "page/Customer/Checkout";
    }

    @GetMapping("/checkoutFromProductDetail")
    public String showFormCheckoutFromProductDetail(
            @RequestParam("quantity") int quantities,
            @RequestParam("productDetailId") int productDetailId,
            @RequestParam("price") int price,
            Model model) {
        System.out.println(quantities);
        System.out.println(price);
        System.out.println(productDetailId);
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Lấy thông tin user từ session
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Nếu không có user trong session, chuyển hướng về trang đăng nhập
        }

        // Dùng userId để lấy giỏ hàng của người dùng
        int userId = user.getUserID();
        List<AddressDTO> addressDTOS = addressService.getAddressByUserId(userId);
        ProductDetailDTO productDetailDTO = productDetailService.getProductDetailById(productDetailId);
        ProductDetailCheckoutDTO productDetailCheckoutDTO = new ProductDetailCheckoutDTO();
        productDetailCheckoutDTO.setProductDetailDTO(productDetailDTO);
        //Tìm product có mã đẻ lấy price,img,size
        ProductDTO productDTO=productService.getProductByProductDetail(productDetailId);
        productDetailCheckoutDTO.setPrice(productDTO.getPrice());
        productDetailCheckoutDTO.setName(productDTO.getProductName());
        productDetailCheckoutDTO.setImage(productDTO.getImageURL());
        productDetailCheckoutDTO.setQuantity(quantities);
productDetailCheckoutDTO.setProductId(productDTO.getProductID());
        List<ProductDetailCheckoutDTO> productDetailCheckoutDTOS = new ArrayList<>();
        productDetailCheckoutDTOS.add(productDetailCheckoutDTO);

        int subTotal=price* quantities;
        int delivery=0;
        int total=subTotal+delivery;
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        model.addAttribute("user", user);
        model.addAttribute("address", addressDTOS);
        model.addAttribute("ProductDetailCheckoutDTO", productDetailCheckoutDTOS);
        model.addAttribute("subTotal", formatter.format(subTotal) + " VNĐ");
        model.addAttribute("delivery",formatter.format(delivery) + " VNĐ");
        model.addAttribute("total", formatter.format(total));


        return "page/Customer/Checkout";
    }

    private String formatAddress(AddressDTO addressDTO) {
        return addressDTO.getStreet() + ", ward " + addressDTO.getWard() + ", district " + addressDTO.getDistrict() + ", " + addressDTO.getCity();
    }

    private Integer extractProductDetailID(String productDetailStr) {
        // Tìm vị trí "productDetailID=" trong chuỗi
        int startIndex = productDetailStr.indexOf("productDetailID=") + "productDetailID=".length();
        // Tìm dấu phẩy đầu tiên sau "productDetailID="
        int endIndex = productDetailStr.indexOf(",", startIndex);
        // Nếu không có dấu phẩy, kết thúc ở cuối chuỗi
        if (endIndex == -1) {
            endIndex = productDetailStr.length();
        }
        // Trích xuất giá trị và chuyển thành số nguyên
        return Integer.parseInt(productDetailStr.substring(startIndex, endIndex).trim());
    }

    @PostMapping("/order/payment/add")
    public String addOrder(@RequestBody Map<String, Object> payload,
                           OrderCheckoutDTO orderCheckoutDTO
    ) throws MessagingException {
        // Lấy thông tin address, total, delivery từ request body
        Integer address = (Integer) payload.get("address");
        Integer total = (Integer) payload.get("total");
        Integer delivery = (Integer) payload.get("delivery");
        Integer paymentCase= (Integer) payload.get("paymentCase");
        System.out.println(paymentCase);
        // Lấy productDetails (mảng sản phẩm) từ request body
        List<Map<String, Object>> productDetails = (List<Map<String, Object>>) payload.get("productDetails");

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Lấy thông tin user từ session
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Nếu không có user trong session, chuyển hướng về trang đăng nhập
        }


        AddressDTO addressDTO = addressService.getAddressById(address);
        String shippingAddress = formatAddress(addressDTO);
        // Dùng userId để lấy giỏ hàng của người dùng
        int userId = user.getUserID();
        //Chuẩn bị dữ liệu order xong
        orderCheckoutDTO.setFeeShip(delivery);
        orderCheckoutDTO.setTotal(total);
        orderCheckoutDTO.setOrderDate(LocalDate.now());
        orderCheckoutDTO.setUser(user);
        orderCheckoutDTO.setStatus("Processing");
        orderCheckoutDTO.setShippingAddress(shippingAddress);

        OrderCheckoutDTO orderSave = orderService.addOrder(orderCheckoutDTO);
        int orderId = orderSave.getOrderID();
        OrderCheckoutDTO orderCheckoutDTODTO = orderService.getById(orderId);

        for (Map<String, Object> productDetail : productDetails) {
            OrderDetailResponeDTO orderDetailResponeDTO = new OrderDetailResponeDTO();
            Number price = (Number) productDetail.get("price");
            orderDetailResponeDTO.setPrice(price.doubleValue());
            orderDetailResponeDTO.setQuantity((Integer) productDetail.get("quantity"));

            String productDetailIdStr = (String) productDetail.get("productDetailId");
            Integer productDetailID = extractProductDetailID(productDetailIdStr);
            ProductDetailDTO productDetailDTO = productDetailService.getProductDetailById(productDetailID);
            orderDetailResponeDTO.setProductDetail(productDetailDTO);
            orderDetailResponeDTO.setOrder(orderCheckoutDTODTO);
            System.out.println(orderDetailResponeDTO);
            OrderDetailResponeDTO orderDetailSave = orderDetailService.addOrderDetail(orderDetailResponeDTO);

        }
        PaymentDTO paymentDTO = new PaymentDTO();
        ReceiptDTO receiptDTO=new ReceiptDTO();
        if(paymentCase==1){
            String idCheckPayment=paymentService.sendAmount(total);
            paymentDTO.setCode(idCheckPayment);
        }

        paymentDTO.setStatus("Pending");
        paymentDTO.setCode("Cash delivery");

        paymentDTO.setPaymentDate(LocalDate.now());
        paymentDTO.setOrder(orderSave);

        System.out.println("Payment gui:"+paymentDTO);
        PaymentDTO paymentSave = paymentService.addPayment(paymentDTO);
        receiptDTO.setPayment(paymentSave);
        receiptDTO.setReceiptDate(LocalDate.now());
        receiptDTO.setTotal(total);
//        ReceiptDTO receiptSave= receiptService.addReceipt(receiptDTO);



        // Xử lý dữ liệu ở đây và trả về phản hồi
        return "redirect:/login";
    }
}