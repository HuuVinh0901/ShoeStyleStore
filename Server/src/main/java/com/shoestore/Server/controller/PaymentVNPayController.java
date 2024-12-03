package com.shoestore.Server.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.shoestore.Server.utils.GetIpAddress;
import com.shoestore.Server.utils.VnPayUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PaymentVNPayController {
    @GetMapping("/payment/VnPay")
    public String getPay(@RequestParam("orderPrice") long orderPrice) throws UnsupportedEncodingException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = orderPrice*100;
        String bankCode = "NCB";

        String vnp_TxnRef = VnPayUtils.getRandomNumber(8);
        String vnp_IpAddr = GetIpAddress.getIpAddress();

        String vnp_TmnCode = VnPayUtils.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        String returnUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VnPayUtils.vnp_ReturnUrl)
                .toUriString();
        vnp_Params.put("vnp_ReturnUrl", returnUrl);
        System.out.println(returnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayUtils.hmacSHA512(VnPayUtils.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayUtils.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }
    @RequestMapping(value = "/payment/result", method = {RequestMethod.GET, RequestMethod.POST})
    public String paymentResult(@RequestParam Map<String, String> params) throws UnsupportedEncodingException {
        System.out.println("Thông tin trả về từ VNPay: " + params);

        // Lấy thông tin từ các tham số trả về
        String vnp_SecureHash = params.get("vnp_SecureHash");

        // Xây dựng dữ liệu hash từ các tham số nhận được
        StringBuilder hashData = new StringBuilder();
        params.remove("vnp_SecureHash");  // Xóa SecureHash ra ngoài vì nó không tham gia tính toán

        // Duyệt qua các tham số và xây dựng chuỗi hashData theo thứ tự bảng chữ cái
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);  // Sắp xếp theo thứ tự chữ cái

        for (String fieldName : fieldNames) {
            String fieldValue = URLDecoder.decode(params.get(fieldName), "UTF-8"); // Giải mã giá trị tham số
            if (fieldValue != null && !fieldValue.isEmpty()) {
                // Mã hóa URL tham số trước khi thêm vào chuỗi hashData
                hashData.append(fieldName)
                        .append("=")
                        .append(fieldValue)
                        .append("&");
            }
        }

        // Xử lý việc bỏ "&" cuối cùng
        String dataToHash = hashData.toString();
        if (dataToHash.endsWith("&")) {
            dataToHash = dataToHash.substring(0, dataToHash.length() - 1);
        }

        // Tính toán lại mã băm từ dữ liệu trả về
        String secureHashCheck = VnPayUtils.hmacSHA512(VnPayUtils.secretKey, dataToHash);

        // In ra thông tin để kiểm tra
        System.out.println("Dữ liệu hash: " + dataToHash);
        System.out.println("Mã bảo mật tính toán: " + secureHashCheck);
        System.out.println("Mã bảo mật từ VNPay: " + vnp_SecureHash);

        // So sánh SecureHash từ VNPay và tính toán của bạn
        if (secureHashCheck.equals(vnp_SecureHash)) {
            // Kiểm tra kết quả giao dịch
            String vnp_ResponseCode = params.get("vnp_ResponseCode");
            if ("00".equals(vnp_ResponseCode)) {
                // Giao dịch thành công
                return "Thanh toán thành công!";
            } else {
                // Giao dịch thất bại
                return "Thanh toán thất bại!";
            }
        } else {
            // Mã bảo mật không hợp lệ
            return "Mã bảo mật không hợp lệ! SecureHash from VNPay: " + vnp_SecureHash + ", calculated SecureHash: " + secureHashCheck;
        }
    }

}
