package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.OrderCheckoutDTO;
import com.shoestore.client.dto.request.PaymentDTO;
import com.shoestore.client.service.EmailService;
import com.shoestore.client.service.PaymentService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EmailService emailService;

    @Override
    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        String apiUrl = "http://localhost:8080/payment/add";
        ResponseEntity<PaymentDTO> response = restTemplate.postForEntity(
                apiUrl, paymentDTO, PaymentDTO.class
        );
        return response.getBody();
    }

    @Override
    public String sendAmount(long orderPrice) throws MessagingException {
        String apiUrl = "http://localhost:8080/payment/VnPay?orderPrice=" + orderPrice;
        String userEmail = "phamhuuvinh912003@gmail.com";
        // Gửi request GET đến server và nhận về URL thanh toán
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, String.class
        );
        String paymentUrl = response.getBody();
        System.out.println(paymentUrl);
        // In ra để kiểm tra
        String subject = "Link Thanh Toán Đơn Hàng";
        String emailContent = """
                <p>Chào bạn,</p>
                <p>Cảm ơn bạn đã đặt hàng tại Shoe Store.</p>
                <p>Vui lòng sử dụng link sau để thanh toán đơn hàng của bạn:</p>
                <p>Trân trọng,<br>Shoe Store</p>
                """+paymentUrl;
        try {
            // 3. Gửi email
            emailService.sendEmail(userEmail, subject, emailContent);
            System.out.println("Mail đã được gửi!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể gửi email: " + e.getMessage());
        }
        // Trả về payment URL
        return response.getBody();
    }
}
