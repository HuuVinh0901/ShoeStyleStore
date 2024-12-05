package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.ProductDTO;
import com.shoestore.client.dto.response.OrderDetailDTO;
import com.shoestore.client.service.EmailService;
import com.shoestore.client.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @PostMapping("/sendInvoiceEmail")
    public Map<String, String> sendInvoiceEmail(@RequestBody Map<String, Object> orderDetails) {
        try {
            // Trích xuất thông tin từ body
            String email = (String) orderDetails.get("email");
            String subject = "Thông Tin Hóa Đơn Đặt Hàng";
            String htmlContent = generateInvoiceEmailContent(orderDetails);

            // Gửi email
            sendEmail(email, subject, htmlContent);

            return Map.of("message", "Email hóa đơn đã được gửi thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return Map.of("message", "Đã có lỗi xảy ra khi gửi email!");
        }
    }

    private void sendEmail(String toEmail, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo("hoductrihieu@gmail.com");
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
    private String generateUrlPayment(Map<String, Object> orderDetails) {
        StringBuilder emailContent = new StringBuilder();

        emailContent.append("<html><body>");

        // Header with logo, shop name, and shop details
        emailContent.append("<div style='text-align: center; margin-bottom: 20px;'>");
        emailContent.append("<img src='https://chonden.com/wp-content/uploads/2021/12/db5508cd17203bcd348a16ae60a36fab-300x300.jpg' alt='Shop Logo' style='max-width: 200px;'><br>");
        emailContent.append("<h2 style='margin: 5px;'>HEHEBOIZ</h2>");
        emailContent.append("<p style='margin: 0;'>Địa chỉ: 123 Shop Street, City</p>");
        emailContent.append("<p style='margin: 0;'>Email: contact@shop.com | Số điện thoại: 0123456789</p>");
        emailContent.append("</div>");

        // Invoice details
        emailContent.append("<h3>Thông tin hóa đơn</h3>");
        emailContent.append("<p><strong>Mã đơn:</strong> " + orderDetails.get("orderID") + "</p>");
        emailContent.append("<p><strong>Trạng thái đơn hàng:</strong> <span style='background: yellow;'>" + orderDetails.get("orderStatus") + "</span></p>");
        emailContent.append("<p><strong>Thanh toán:</strong> <span style='background: yellow;'>" + orderDetails.get("orderPayment") + "</span></p>");


        // Customer details section
        emailContent.append("<h3>Thông tin khách hàng</h3>");
        emailContent.append("<p><strong>Tên khách hàng:</strong> " + orderDetails.get("customerName") + "</p>");
        emailContent.append("<p><strong>Số điện thoại:</strong> " + orderDetails.get("customerPhone") + "</p>");
        emailContent.append("<p><strong>Địa chỉ giao hàng:</strong> " + orderDetails.get("customerAddress") + "</p>");

        emailContent.append("</body></html>");

        return emailContent.toString();
    }
    private String generateInvoiceEmailContent(Map<String, Object> orderDetails) {
        StringBuilder emailContent = new StringBuilder();

        emailContent.append("<html><body>");

        // Header with logo, shop name, and shop details
        emailContent.append("<div style='text-align: center; margin-bottom: 20px;'>");
        emailContent.append("<img src='https://chonden.com/wp-content/uploads/2021/12/db5508cd17203bcd348a16ae60a36fab-300x300.jpg' alt='Shop Logo' style='max-width: 200px;'><br>");
        emailContent.append("<h2 style='margin: 5px;'>HEHEBOIZ</h2>");
        emailContent.append("<p style='margin: 0;'>Địa chỉ: 123 Shop Street, City</p>");
        emailContent.append("<p style='margin: 0;'>Email: contact@shop.com | Số điện thoại: 0123456789</p>");
        emailContent.append("</div>");

        // Invoice details
        emailContent.append("<h3>Thông tin hóa đơn</h3>");
        emailContent.append("<p><strong>Mã đơn:</strong> " + orderDetails.get("orderID") + "</p>");
        emailContent.append("<p><strong>Trạng thái đơn hàng:</strong> <span style='background: yellow;'>" + orderDetails.get("orderStatus") + "</span></p>");
        emailContent.append("<p><strong>Thanh toán:</strong> <span style='background: yellow;'>" + orderDetails.get("orderPayment") + "</span></p>");


        // Customer details section
        emailContent.append("<h3>Thông tin khách hàng</h3>");
        emailContent.append("<p><strong>Tên khách hàng:</strong> " + orderDetails.get("customerName") + "</p>");
        emailContent.append("<p><strong>Số điện thoại:</strong> " + orderDetails.get("customerPhone") + "</p>");
        emailContent.append("<p><strong>Địa chỉ giao hàng:</strong> " + orderDetails.get("customerAddress") + "</p>");

        // Product list section
        emailContent.append("<h3>Danh sách sản phẩm:</h3>");
        emailContent.append("<table border='1' cellpadding='10' style='border-collapse: collapse; width: 100%;'>");
        emailContent.append("<tr style='background-color: #f2f2f2;'><th>Sản phẩm</th><th>Số lượng</th><th>Tổng</th></tr>");

        // Loop through product list
        for (Map<String, Object> product : (List<Map<String, Object>>) orderDetails.get("products")) {
            emailContent.append("<tr>");
            emailContent.append("<td>" + product.get("productName") + "</td>");
            emailContent.append("<td>" + product.get("quantity") + "</td>");
            emailContent.append("<td>" + product.get("formattedTotalPrice") + "</td>");
            emailContent.append("</tr>");
        }
        emailContent.append("</table>");

        // Summary row with right-aligned values
        emailContent.append("<table style='width: 100%; margin-top: 20px;'>");
        emailContent.append("<tr><td style='text-align: right;'><strong>Phí vận chuyển:</strong></td><td style='text-align: right;'>" + orderDetails.get("feeShip") + "</td></tr>");
        emailContent.append("<tr><td style='text-align: right;'><strong>Giảm Giá:</strong></td><td style='text-align: right;'>" + orderDetails.get("discountVoucher") + "</td></tr>");
        emailContent.append("<tr><td style='text-align: right;'><strong>Tổng tiền đã thanh toán:</strong></td><td style='text-align: right;'>" + orderDetails.get("totalAmount") + "</td></tr>");
        emailContent.append("</table>");

        emailContent.append("</body></html>");

        return emailContent.toString();
    }


}
