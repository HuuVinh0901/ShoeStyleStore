package com.shoestore.Server.controller;

import com.shoestore.Server.utils.VnPayUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vnpay")
public class VNPayTestController {

    @GetMapping("/return")
    public String vnpayReturn(@RequestParam Map<String, String> params) throws UnsupportedEncodingException {
        // Xử lý tương tự như mã ở trên
        String secretKey = "VAG3TSYGQTUS77S0SGBFFFGOCULQCVRP";
        String vnp_SecureHash = params.get("vnp_SecureHash");

        // Tạo chuỗi hashData
        StringBuilder hashData = new StringBuilder();
        params.remove("vnp_SecureHash");
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        for (String fieldName : fieldNames) {
            String fieldValue = URLDecoder.decode(params.get(fieldName), "UTF-8");
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append("=").append(fieldValue).append("&");
            }
        }

        // Loại bỏ dấu '&' cuối cùng
        if (hashData.length() > 0) {
            hashData.deleteCharAt(hashData.length() - 1);
        }

        // Tính toán hash
        String calculatedHash = VnPayUtils.hmacSHA512(secretKey, hashData.toString());

        // So sánh
        if (calculatedHash.equalsIgnoreCase(vnp_SecureHash)) {
            return "Mã bảo mật khớp!";
        } else {
            return "Mã bảo mật không khớp!";
        }
    }
}

