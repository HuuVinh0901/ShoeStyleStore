package com.shoestore.client.configs;

import com.shoestore.client.security.CustomUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private HttpSession session;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    session.setAttribute("user", userDetails);  // Lưu thông tin người dùng vào session
    System.out.println(12312);
    String role = authentication.getAuthorities().toArray()[0].toString();

    if (role.contains("ROLE_Admin")) {
      response.sendRedirect("/admin");  // Admin
    } else if (role.contains("ROLE_Customer")) {
      response.sendRedirect("/customer");  // Customer
    } else {
      response.sendRedirect("/login");  // Nếu không có quyền, quay lại login
    }
  }
}
