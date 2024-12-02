package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.AddressDTO;
import com.shoestore.client.dto.request.OrderDTO;
import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.service.AddressService;
import com.shoestore.client.service.OrderService;
import com.shoestore.client.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class AccountController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;
    UserDTO user = new UserDTO();
    @GetMapping("/customer/account")
    public String showAccount(Model model) {
      user = (UserDTO) session.getAttribute("user");
      // Kiểm tra nếu người dùng tồn tại
      if (user != null) {
        // Truyền tên người dùng vào model
        System.out.println(user.getName());
        model.addAttribute("userName", user.getName());
      } else {
        model.addAttribute("userName", "Guest");
      }
      return "/page/Customer/Account";
    }

    @Autowired
    private OrderService orderService;

  @GetMapping("/customer/account/my-orders")
  public String showMyOrders(Model model, HttpSession session) {
    System.out.println(user.getName());
    List<OrderDTO> allOrders = orderService.getOrdersByUserId(user.getUserID());
    System.out.println(allOrders);
    // Phân loại đơn hàng
    List<OrderDTO> processingOrders = allOrders.stream()
            .filter(order -> order.getStatus().equalsIgnoreCase("Processing"))
            .toList();

    List<OrderDTO> deliveredOrders = allOrders.stream()
            .filter(order -> order.getStatus().equalsIgnoreCase("Delivered"))
            .toList();

    model.addAttribute("processingOrders", processingOrders);
    model.addAttribute("deliverdOrders", deliveredOrders);

    return "page/Customer/MyOrders";
  }


  @GetMapping("/customer/account/my-account")
  public String showMyAccount(Model model) {
    model.addAttribute("user", user);
      return "page/Customer/MyAccount";
  }

  @Autowired
  private AddressService addressService;
  @GetMapping("/customer/account/my-address")
  public String showAddress(Model model) {
    List<AddressDTO> addressDTOList = addressService.getAddressByUserId(user.getUserID());
    List<String> formattedAddresses = addressDTOList.stream()
            .map(this::formatAddress)
            .toList();
    model.addAttribute("user", user);
    model.addAttribute("addresses", formattedAddresses);
    return "page/Customer/Address";
  }

  private String formatAddress(AddressDTO addressDTO) {
    return addressDTO.getStreet() + ", ward " + addressDTO.getWard() + ", district " + addressDTO.getDistrict() + ", " + addressDTO.getCity();
  }

  @PostMapping("/customer/account/update-account")
  public String updateAccount(@ModelAttribute UserDTO updatedUser, HttpSession session) {
    // Lấy thông tin người dùng hiện tại từ session
    UserDTO currentUser = (UserDTO) session.getAttribute("user");

    if (currentUser != null) {
      // Cập nhật thông tin người dùng qua API
      UserDTO updated = userService.updateUser(currentUser.getUserID(), updatedUser);

      // Cập nhật session với thông tin mới
      session.setAttribute("user", updated);
    }

    return "redirect:/customer/account";
  }

}
