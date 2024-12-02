package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.CartDTO;
import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.security.CustomUserDetailService;
import com.shoestore.client.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class LoginController {

  @Autowired
  private UserService userService;
  @Autowired
  private CustomUserDetailService customUserDetailService;
  @Autowired
  private HttpSession session;
  // Đăng nhập
  @GetMapping("/login")
  public String login() {
    return "page/Login";
  }

  @PostMapping("/login/auth")
  public String login(@ModelAttribute UserDTO userDTO, HttpServletRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      UserDTO user = userService.findByEmail(userDTO.getEmail());

      if (user != null) {
        session.setAttribute("user", user);
        String roleName = user.getRole().getName();
        if ("Admin".equals(roleName)) {
          return "redirect:/admin/orders/Home";  // Admin
        } else if ("Customer".equals(roleName)) {
          return "redirect:/customer/home";  // Customer
        }
      }
    }

    return "redirect:/login?error";  // Trở về login nếu không đăng nhập thành công
  }


  @GetMapping("/register")
  public String showSignUpForm() {
    return "page/Register";
  }

  @PostMapping("/register/auth")
  public String registerUser(@ModelAttribute UserDTO userDTO, Model model) {
    try {
      userService.save(userDTO);
      return "redirect:/login";
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      return "page/Register";
    }
  }


}
