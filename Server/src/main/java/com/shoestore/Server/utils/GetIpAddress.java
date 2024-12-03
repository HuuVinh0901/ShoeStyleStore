package com.shoestore.Server.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIpAddress {
    public static String getIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();  // Trả về địa chỉ IP của máy tính hiện tại
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
}
