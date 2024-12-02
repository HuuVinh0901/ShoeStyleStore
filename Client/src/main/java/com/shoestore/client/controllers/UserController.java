package com.shoestore.client.controllers;

import com.shoestore.client.dto.request.RoleDTO;
import com.shoestore.client.dto.request.UserDTO;
import com.shoestore.client.dto.request.VoucherDTO;
import com.shoestore.client.dto.response.UserResponseDTO;
import com.shoestore.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Show the form to create a new user
    @GetMapping("/add")
    public String showCreateUserForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "page/Admin/ThemMoiUser"; // The HTML form to add a new user
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") UserDTO user,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Error: " + result.getAllErrors());
            return "page/Admin/ThemMoiUser";
        }
        System.out.println("Received User: " + user); // Kiểm tra thông tin user nhận được
        userService.save(user); // Giả sử bạn có một service lưu user vào database
        return "redirect:/users/list";
    }


    // Show list of users
    @GetMapping("/list")
    public String showUserList(Model model) {
        List<UserDTO> users = userService.getUsersFromServer();
        model.addAttribute("users", users);
        return "page/Admin/DanhSachUser"; // The HTML page to show the user list
    }

    // API tìm kiếm người dùng
    @GetMapping("/search")
    public String timKiem(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "roleName", required = false) String roleName,
                          @RequestParam(value = "status", required = false) String status, Model model) {
        System.out.println("RoleName: " + roleName);
        System.out.println("UserName: " + name);
        System.out.println("Status: " + status);

        List<UserDTO> users = userService.searchUsers(name, roleName, status);
        model.addAttribute("users", users);
        return "page/Admin/DanhSachUser";  // Trả về trang danh sách người dùng đã được lọc
    }
    @GetMapping("/edit/{Id}")
    public String editUser(@PathVariable("Id") int id, Model model) {
        // Fetch the user by userId from the database
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "page/Admin/ChinhSuaUser"; // returns the view name (e.g., editUser.html or editUser.jsp)
        } else {
            return "redirect:/users/list"; // Redirect to user list if user not found
        }
    }
}
