package com.shoestore.Server.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import com.shoestore.Server.dto.DataResponse;
import com.shoestore.Server.dto.PaymentDTO;
import com.shoestore.Server.utils.GetIpAddress;
import com.shoestore.Server.utils.VnPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PaymentVNPayController {
    @GetMapping("/payment/VnPay")
    public String getPay(@RequestParam("orderPrice") long orderPrice) throws UnsupportedEncodingException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = orderPrice * 100;
        String bankCode = "NCB";

        String vnp_TxnRef = VnPayUtils.getRandomNumber(8);
        String vnp_IpAddr = GetIpAddress.getIpAddress();
        System.out.println(vnp_IpAddr);
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

    @GetMapping("/vn-pay-callback")
    public ResponseEntity<DataResponse<PaymentDTO.VNPayResponse>> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");

        if ("00".equals(status)) {
            // Giao dịch thành công
            return ResponseEntity.ok(DataResponse.<PaymentDTO.VNPayResponse>builder()
                    .data(List.of(new PaymentDTO.VNPayResponse("00", "Success", "")))
                    .build());
        } else {
            // Giao dịch không thành công
            return ResponseEntity.ok(DataResponse.<PaymentDTO.VNPayResponse>builder()
                    .data(List.of(new PaymentDTO.VNPayResponse("99", "Failed", "")))
                    .build());
        }
    }

}