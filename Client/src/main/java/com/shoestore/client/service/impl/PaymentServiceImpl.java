package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.CartDTO;
import com.shoestore.client.dto.request.OrderCheckoutDTO;
import com.shoestore.client.dto.request.PaymentCheckDTO;
import com.shoestore.client.dto.request.PaymentDTO;
import com.shoestore.client.dto.response.DataResponse;
import com.shoestore.client.service.EmailService;
import com.shoestore.client.service.PaymentService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
        UriComponentsBuilder UriComponentsBuilder = null;
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(paymentUrl).build();
        System.out.println(uriComponents.getQueryParams().getFirst("vnp_TxnRef"));
        return uriComponents.getQueryParams().getFirst("vnp_TxnRef");
    }
    public boolean checkPaymentStatus(String txnRef) {
//         Gửi yêu cầu GET đến server để kiểm tra trạng thái giao dịch
        String checkStatusUrl = "http://localhost:8080/vn-pay-callback?vnp_TxnRef=" + txnRef;
        ResponseEntity<PaymentCheckDTO.VNPayResponse> responseEntity = restTemplate.getForEntity(checkStatusUrl, PaymentCheckDTO.VNPayResponse.class);

        // Kiểm tra phản hồi và trạng thái giao dịch
        PaymentCheckDTO.VNPayResponse paymentResponse = responseEntity.getBody();
        return "00".equals(paymentResponse.getCode());  // Nếu mã trả về là "00" thì giao dịch thành công
    }
    @Override
    public PaymentDTO getPaymentByOrderId(int id) {

        String apiUrl = "http://localhost:8080/payment/orderId/" + id;
        ResponseEntity<PaymentDTO> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, PaymentDTO.class
        );
        System.out.println("Response Body: " + response.getBody());
        return response.getBody();

    }
}
